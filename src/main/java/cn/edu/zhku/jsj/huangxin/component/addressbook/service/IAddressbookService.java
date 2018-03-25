package cn.edu.zhku.jsj.huangxin.component.addressbook.service;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.*;
import cn.edu.zhku.jsj.huangxin.component.base.model.Page;
import cn.edu.zhku.jsj.huangxin.component.base.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface IAddressbookService extends IBaseService {
	/**
	 * 获取accessToken
	 * @param searchMap
	 * @return List<AccessTokenPO>
	 */
	List<AccessTokenPO> getAccessTokenPO(Map<String,Object> searchMap);

	/**
	 * 通过agentCode获取应用
	 * @param agentCode
	 * @return AgentPO
	 */
	AgentPO getAgentPO(String agentCode);

	/**
	 * 获取后台登录用户
	 * @param searchMap
	 * @return AdminUserPO
	 */
	AdminUserPO getAdminUser(Map<String, Object> searchMap);

	/**
	 * 获取部门详情
	 * @param searchMap
	 * @return TbDepartmentInfoPO
	 */
	TbDepartmentInfoPO getDepartmentInfo(Map<String, Object> searchMap);

	/**
	 * 获取用户详情
	 * @param searchMap
	 * @return TbUserInfoPO
	 */
	TbUserInfoPO getUserInfo(Map<String, Object> searchMap);
	/**
	 * 获取后台登录用户详情
	 * @param searchMap
	 * @return TbAdminUserPO
	 */
	TbAdminUserPO getAdminUserInfo(Map<String, Object> searchMap);

	/**
	 * 获取部门列表
	 * @param searchMap
	 * @return List<TbDepartmentInfoPO>
	 */
	List<TbDepartmentInfoPO> getDepartmentList(Map<String, Object> searchMap);
	/**
	 * 获取用户列表
	 * @param searchMap
	 * @param page 分页对象
	 */
	void getUserList(Map<String, Object> searchMap, Page<TbUserInfoPO> page);
}
