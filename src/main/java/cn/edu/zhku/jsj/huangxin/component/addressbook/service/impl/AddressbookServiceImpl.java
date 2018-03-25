package cn.edu.zhku.jsj.huangxin.component.addressbook.service.impl;

import cn.edu.zhku.jsj.huangxin.component.addressbook.dao.AddressbookDAO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.*;
import cn.edu.zhku.jsj.huangxin.component.addressbook.service.IAddressbookService;
import cn.edu.zhku.jsj.huangxin.component.addressbook.util.AddressbookUtil;
import cn.edu.zhku.jsj.huangxin.component.base.model.HttpResult;
import cn.edu.zhku.jsj.huangxin.component.base.model.Page;
import cn.edu.zhku.jsj.huangxin.component.base.model.WeiXinUser;
import cn.edu.zhku.jsj.huangxin.component.base.service.impl.BaseService;
import cn.edu.zhku.jsj.huangxin.component.base.util.AssertUtils;
import cn.edu.zhku.jsj.huangxin.component.base.util.WeiXinUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.json.JsonObject;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("addressbookService")
public class AddressbookServiceImpl extends BaseService implements IAddressbookService {

	private final static transient Logger logger = LoggerFactory.getLogger(AddressbookServiceImpl.class);
	private AddressbookDAO addressbookDAO;

	public AddressbookDAO getAddressbookDAO() {
		return addressbookDAO;
	}
	@Resource
	public void setAddressbookDAO(AddressbookDAO addressbookDAO) {
		this.addressbookDAO = addressbookDAO;
	}

	@Override
	public List<AccessTokenPO> getAccessTokenPO(Map<String, Object> searchMap) {
		return addressbookDAO.getAccessTokenPO(searchMap);
	}

	@Override
	public AgentPO getAgentPO(String agentCode) {
		return addressbookDAO.getAgentPO(agentCode);
	}

	@Override
	public AdminUserPO getAdminUser(Map<String, Object> searchMap) {
		return addressbookDAO.getAdminUser(searchMap);
	}

	@Override
	public TbDepartmentInfoPO getDepartmentInfo(Map<String, Object> searchMap) {
		return addressbookDAO.getDepartmentInfo(searchMap);
	}

	@Override
	public TbUserInfoPO getUserInfo(Map<String, Object> searchMap) {
		return addressbookDAO.getUserInfo(searchMap);
	}

	@Override
	public TbAdminUserPO getAdminUserInfo(Map<String, Object> searchMap) {
		return addressbookDAO.getAdminUserInfo(searchMap);
	}

	@Override
	public List<TbDepartmentInfoPO> getDepartmentList(Map<String, Object> searchMap) {
		return addressbookDAO.getDepartmentList(searchMap);
	}

	@Override
	public void getUserList(Map<String, Object> searchMap, Page<TbUserInfoPO> page) {

		PageHelper.startPage(page.getCurPage(), page.getPageSize());
		List<TbUserInfoPO> list = addressbookDAO.getUserList(searchMap);
		PageInfo<TbUserInfoPO> pageInfo = new PageInfo<>(list);
		page.setData(list);
		page.setTotalPages(pageInfo.getPages());
		page.setTotalRows((int) pageInfo.getTotal());

	}

	@Override
	public String updateUser(TbUserInfoPO userInfo) {
		String result = null;
		TbUserInfoPO oldUserInfo = searchPOByPk(TbUserInfoPO.class,userInfo.getId());
		if(AssertUtils.isEmpty(oldUserInfo)){
			result = "更新失败，用户不存在";
		}else{
			TbDepartmentInfoPO departmentInfoPO = searchPOByPk(TbDepartmentInfoPO.class,userInfo.getDeptId());
			if(AssertUtils.isEmpty(departmentInfoPO)){
				result = "更新失败，用户的部门不存在";
			}else{
				WeiXinUser weiXinUser = new WeiXinUser();
				weiXinUser.setUserid(oldUserInfo.getUserId());
				weiXinUser.setName(userInfo.getUserName());
				weiXinUser.setDepartment(departmentInfoPO.getWxId());
				weiXinUser.setPosition(userInfo.getPosition());
				weiXinUser.setGender(userInfo.getSex());
				weiXinUser.setEmail(userInfo.getEmail());
				weiXinUser.setTelephone(userInfo.getTelephone());
				HttpResult httpResult = WeiXinUtils.updateUser(WeiXinUtils.getAccessToken("org", AddressbookUtil.getAdminOrgId()),weiXinUser);
				logger.error(httpResult.toString());
				JSONObject jsonObject = JSONObject.parseObject(httpResult.getContent());
				if(httpResult.getCode()!=200){
					result = "更新微信用户失败！";
				}else{
					if(!"0".equals(jsonObject.getString("errcode"))){
						result = "更新微信用户失败！错误码为：" + jsonObject.getString("errcode");
					}else{
						userInfo.setUpdateTime(new Date());
						updatePO(userInfo);
					}
				}
			}
		}
		return result;
	}

	@Override
	public String delUserById(String userId) {
		String result = null;
		TbUserInfoPO userInfo = searchPOByPk(TbUserInfoPO.class,userId);
		if(AssertUtils.isEmpty(userInfo)){
			result = "删除失败，用户不存在";
		}else{
			HttpResult httpResult = WeiXinUtils.delUser(WeiXinUtils.getAccessToken("org", AddressbookUtil.getAdminOrgId()),userInfo.getUserId());
			logger.error(httpResult.toString());
			JSONObject jsonObject = JSONObject.parseObject(httpResult.getContent());
			if(httpResult.getCode() != 200){
				result = "删除微信用户失败！";
			}else{
				if(!"0".equals(jsonObject.getString("errcode"))){
					result = "删除微信用户失败！错误码为："+jsonObject.getString("errcode");
				}else{
					deletePO(TbUserInfoPO.class,userId);
				}
			}
		}
		return result;
	}

	@Override
	public String addUser(TbUserInfoPO tbUserInfoPO) {
		String result = null;
		TbDepartmentInfoPO departmentInfoPO = searchPOByPk(TbDepartmentInfoPO.class,tbUserInfoPO.getDeptId());
		if(AssertUtils.isEmpty(departmentInfoPO)){
			result = "添加失败，用户的部门不存在";
		}else{
			String userId= UUID.randomUUID().toString();
			WeiXinUser weiXinUser = new WeiXinUser();
			weiXinUser.setUserid(userId);
			weiXinUser.setName(tbUserInfoPO.getUserName());
			weiXinUser.setDepartment(departmentInfoPO.getWxId());
			weiXinUser.setPosition(tbUserInfoPO.getPosition());
			weiXinUser.setGender(tbUserInfoPO.getSex());
			weiXinUser.setEmail(tbUserInfoPO.getEmail());
			weiXinUser.setTelephone(tbUserInfoPO.getTelephone());
			HttpResult httpResult = WeiXinUtils.addUser(WeiXinUtils.getAccessToken("org", AddressbookUtil.getAdminOrgId()),weiXinUser);
			logger.error(httpResult.toString());
			JSONObject jsonObject = JSONObject.parseObject(httpResult.getContent());
			if(httpResult.getCode()!=200){
				result = "新增微信用户失败！";
			}else{
				if(!"0".equals(jsonObject.getString("errcode"))){
					result = "新增微信用户失败！错误码为：" + jsonObject.getString("errcode");
				}else{
					updatePO(tbUserInfoPO);
				}
			}
		}
		return result;
	}
}
