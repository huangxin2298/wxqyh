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

	/**
	 * 更新用户信息
	 * @param userInfo
	 */
	String updateUser(TbUserInfoPO userInfo);

	/**
	 * 删除成员
	 * @param userId 成员id
	 * @return
	 */
	String delUserById(String userId);
	/**
	 * 添加成员
	 * @param tbUserInfoPO 成员信息
	 * @return
	 */
	String addUser(TbUserInfoPO tbUserInfoPO);

	/**
	 * 更新部门
	 * @param tbDepartmentInfoPO 部门信息
	 * @return 更新结果
	 */
	String updateDepatment(TbDepartmentInfoPO tbDepartmentInfoPO);

	/**
	 * 删除部门
	 * @param deptId
	 * @return
	 */
	String delDepartmentById(String deptId);

	/**
	 * 新增部门
	 * @param tbDepartmentInfoPO 部门信息
	 * @return 新增结果
	 */
	String addDepartment(TbDepartmentInfoPO tbDepartmentInfoPO);

	/**
	 * 获取子部门id
	 * @param searchMap
	 * @return
	 */
	List<String> getChildDeptId(Map<String, Object> searchMap);

}
