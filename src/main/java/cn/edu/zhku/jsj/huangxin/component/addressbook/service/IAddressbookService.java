package cn.edu.zhku.jsj.huangxin.component.addressbook.service;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AccessTokenPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AdminUserPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AgentPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbDepartmentInfoPO;
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
}
