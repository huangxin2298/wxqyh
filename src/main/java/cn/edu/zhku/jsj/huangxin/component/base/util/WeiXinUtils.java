package cn.edu.zhku.jsj.huangxin.component.base.util;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AccessTokenPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AgentPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.OrgPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.service.IAddressbookService;
import cn.edu.zhku.jsj.huangxin.component.base.model.HttpResult;
import cn.edu.zhku.jsj.huangxin.component.base.model.WeiXinDept;
import cn.edu.zhku.jsj.huangxin.component.base.model.WeiXinUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class WeiXinUtils {

	private final static transient Logger logger = LoggerFactory.getLogger(WeiXinUtils.class);
	private static IAddressbookService addressbookService = SpringContextHolder.getBean(IAddressbookService.class);

	/**
	 * 获取access_token
	 * @param agentCode 应用code（获取机构的Token时填org）
	 * @param orgId 机构id
	 * @return
	 */
	public static String getAccessToken(String agentCode,String orgId){
		String accessToken = null;
		boolean isGet = false;
		if(RedisUtils.hasKey(agentCode)){
			accessToken = (String) RedisUtils.get(agentCode);
			if(!AssertUtils.isEmpty(accessToken)){
				isGet = true;
			}
		}
		if(!isGet){
			Map<String, Object> searchMap = new HashMap<>();
			searchMap.put("agent_code",agentCode);
			searchMap.put("org_id", orgId);
			List<AccessTokenPO> accessTokenPOList = addressbookService.getAccessTokenPO(searchMap);
			if(!AssertUtils.isEmpty(accessTokenPOList)){
				AccessTokenPO accessTokenPO = accessTokenPOList.get(0);
				if(accessTokenPO.getValidTime().getTime()>new Date().getTime()){
					accessToken = accessTokenPO.getToken();
					if(!AssertUtils.isEmpty(accessToken)){
						RedisUtils.set(agentCode,accessToken,7000);
						isGet = true;
					}
				}
			}
		}
		if(!isGet){
			OrgPO orgPO = addressbookService.searchPOByPk(OrgPO.class, orgId);
			String corpSecret = null;
			if("org".equals(agentCode)){
				corpSecret = orgPO.getCorpSecret();
			}else{
				AgentPO agentPO = addressbookService.getAgentPO(agentCode);
				corpSecret = agentPO.getSecret();
			}
			if(!AssertUtils.isEmpty(orgPO.getCorpId()) && !AssertUtils.isEmpty(corpSecret)){
				Map<String, String> map = new HashMap<>();
				map.put("corpid",orgPO.getCorpId());
				map.put("corpsecret",corpSecret);
				HttpResult httpResult = HttpUtils.doGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken",map);
				logger.error(httpResult.toString());
				if(httpResult.getCode() == 200){
					JSONObject jsonObject = JSONObject.parseObject(httpResult.getContent());
					String access_token = jsonObject.getString("access_token");
					if(!AssertUtils.isEmpty(access_token)){
						Date now = new Date();
						accessToken = access_token;
						RedisUtils.set(agentCode,accessToken,7000);
						AccessTokenPO accessTokenPO = new AccessTokenPO();
						accessTokenPO.setId(UUID.randomUUID().toString());
						accessTokenPO.setOrgId(orgId);
						accessTokenPO.setAgentCode(agentCode);
						accessTokenPO.setCreateTime(now);
						accessTokenPO.setToken(accessToken);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(now);
						calendar.add(Calendar.SECOND,7200);
						Date validTime = calendar.getTime();
						accessTokenPO.setValidTime(validTime);
						addressbookService.insertPO(accessTokenPO);
					}
				}
			}
		}
		return accessToken;
	}

	/**
	 * 获取部门列表
	 * @param accessToken 调用接口凭证
	 * @param deptWxId 	部门id。获取指定部门及其下的子部门。 如果不填，默认获取全量组织架构
	 * @return
	 */
	public static HttpResult getDeptList(String accessToken, String deptWxId){
		Map<String, String> map = new HashMap<>();
		map.put("access_token",accessToken);
		if(!AssertUtils.isEmpty(deptWxId)){
			map.put("id",deptWxId);
		}
		return HttpUtils.doGet("https://qyapi.weixin.qq.com/cgi-bin/department/list",map);
	}

	/**
	 * 获取部门成员详情
	 * @param accessToken 调用接口凭证
	 * @param deptWxId 	获取的部门id
	 * @param isFetchChild 	是否递归获取子部门的成员(0-否，1-是)
	 * @return HttpResult
	 */
	public static HttpResult getUserList(String accessToken, String deptWxId, String isFetchChild){
		HttpResult httpResult = new HttpResult();
		Map<String, String> map = new HashMap<>();
		map.put("access_token",accessToken);
		map.put("department_id",deptWxId);
		if(!AssertUtils.isEmpty(isFetchChild) && ("1".equals(isFetchChild) || "0".equals(isFetchChild))){
			map.put("fetch_child",isFetchChild);
		}
		httpResult = HttpUtils.doGet("https://qyapi.weixin.qq.com/cgi-bin/user/list",map);
		return httpResult;
	}
	/**
	 * 更新成员
	 * @param accessToken 调用接口凭证
	 * @param weiXinUser 微信用户信息
	 * @return HttpResult
	 */
	public static HttpResult updateUser(String accessToken, WeiXinUser weiXinUser){
		HttpResult httpResult = new HttpResult();
		String wxUser = JSON.toJSONString(weiXinUser);
		httpResult = HttpUtils.doPostJson("https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token="+accessToken,wxUser);
		return httpResult;
	}
	/**
	 * 删除成员
	 * @param accessToken 调用接口凭证
	 * @param userId 微信userid
	 * @return HttpResult
	 */
	public static HttpResult delUser(String accessToken, String userId){
		HttpResult httpResult = new HttpResult();
		Map<String, String> map = new HashMap<>();
		map.put("access_token",accessToken);
		map.put("userid",userId);
		httpResult = HttpUtils.doGet("https://qyapi.weixin.qq.com/cgi-bin/user/delete",map);
		return httpResult;
	}
	/**
	 * 新增成员
	 * @param accessToken 调用接口凭证
	 * @param weiXinUser 微信用户信息
	 * @return HttpResult
	 */
	public static HttpResult addUser(String accessToken, WeiXinUser weiXinUser){
		HttpResult httpResult = new HttpResult();
		String wxUser = JSON.toJSONString(weiXinUser);
		httpResult = HttpUtils.doPostJson("https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token="+accessToken,wxUser);
		return httpResult;
	}

	/**
	 * 更新部门
	 * @param accessToken 调用接口凭证
	 * @param weiXinDept 微信部门信息
	 * @return HttpResult
	 */
	public static HttpResult updateDept(String accessToken, WeiXinDept weiXinDept) {
		HttpResult httpResult = new HttpResult();
		String wxDept = JSON.toJSONString(weiXinDept);
		httpResult = HttpUtils.doPostJson("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+accessToken,wxDept);
		return httpResult;
	}

	/**
	 * 删除部门
	 * @param accessToken 调用接口凭证
	 * @param deptId 微信部门id
	 * @return HttpResult
	 */
	public static HttpResult delDept(String accessToken, String deptId){
		HttpResult httpResult = new HttpResult();
		Map<String, String> map = new HashMap<>();
		map.put("access_token",accessToken);
		map.put("id",deptId);
		httpResult = HttpUtils.doGet("https://qyapi.weixin.qq.com/cgi-bin/department/delete",map);
		return httpResult;
	}
	/**
	 * 新增部门
	 * @param accessToken 调用接口凭证
	 * @param weiXinDept 微信部门信息
	 * @return HttpResult
	 */
	public static HttpResult addDept(String accessToken, WeiXinDept weiXinDept){
		HttpResult httpResult = new HttpResult();
		String wxDept = JSON.toJSONString(weiXinDept);
		httpResult = HttpUtils.doPostJson("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken,wxDept);
		return httpResult;
	}

	public static HttpResult getUserinfo(String accessToken, String code) {
		HttpResult httpResult = new HttpResult();
		Map<String, String> map = new HashMap<>();
		map.put("access_token",accessToken);
		map.put("code",code);
		httpResult = HttpUtils.doGet("https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+accessToken,map);
		return httpResult;
	}
}
