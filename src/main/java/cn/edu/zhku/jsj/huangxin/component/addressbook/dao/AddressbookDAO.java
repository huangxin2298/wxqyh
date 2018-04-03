package cn.edu.zhku.jsj.huangxin.component.addressbook.dao;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AddressbookDAO {
	/**
	 * 获取accessToken
	 * @param searchMap
	 * @return List<AccessTokenPO>
	 */
	List<AccessTokenPO> getAccessTokenPO(@Param("searchMap") Map<String,Object> searchMap);
	/**
	 * 通过agentCode获取应用
	 * @param agentCode
	 * @return AgentPO
	 */
	AgentPO getAgentPO(@Param("agentCode") String agentCode);
	/**
	 * 获取后台登录用户
	 * @param searchMap
	 * @return AdminUserPO
	 */
	AdminUserPO getAdminUser(@Param("searchMap")Map<String, Object> searchMap);
	/**
	 * 获取部门详情
	 * @param searchMap
	 * @return TbDepartmentInfoPO
	 */
	TbDepartmentInfoPO getDepartmentInfo(@Param("searchMap")Map<String, Object> searchMap);
	/**
	 * 获取用户详情
	 * @param searchMap
	 * @return TbUserInfoPO
	 */
	TbUserInfoPO getUserInfo(@Param("searchMap")Map<String, Object> searchMap);
	/**
	 * 获取后台登录用户详情
	 * @param searchMap
	 * @return TbAdminUserPO
	 */
	TbAdminUserPO getAdminUserInfo(@Param("searchMap")Map<String, Object> searchMap);
	/**
	 * 获取部门列表
	 * @param searchMap
	 * @return List<TbDepartmentInfoPO>
	 */
	List<TbDepartmentInfoPO> getDepartmentList(@Param("searchMap")Map<String, Object> searchMap);
	/**
	 * 获取用户列表
	 * @param searchMap
	 */
	List<TbUserInfoPO> getUserList(@Param("searchMap")Map<String, Object> searchMap);
	/**
	 * 获取子部门id
	 * @param searchMap
	 * @return
	 */
	List<String> getChildDeptId(@Param("searchMap")Map<String, Object> searchMap);
}
