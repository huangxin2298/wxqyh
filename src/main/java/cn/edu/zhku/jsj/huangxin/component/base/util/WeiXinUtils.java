package cn.edu.zhku.jsj.huangxin.component.base.util;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AccessTokenPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AgentPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.OrgPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.service.IAddressbookService;
import cn.edu.zhku.jsj.huangxin.component.base.model.HttpResult;
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
}
