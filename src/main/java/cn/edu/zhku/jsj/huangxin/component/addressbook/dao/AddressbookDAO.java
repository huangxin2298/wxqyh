package cn.edu.zhku.jsj.huangxin.component.addressbook.dao;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AddressbookDAO {

	List<AccessTokenPO> getAccessTokenPO(@Param("searchMap") Map<String,Object> searchMap);

	AgentPO getAgentPO(@Param("agentCode") String agentCode);

	AdminUserPO getAdminUser(@Param("searchMap")Map<String, Object> searchMap);

	TbDepartmentInfoPO getDepartmentInfo(@Param("searchMap")Map<String, Object> searchMap);

	TbUserInfoPO getUserInfo(@Param("searchMap")Map<String, Object> searchMap);

	TbAdminUserPO getAdminUserInfo(@Param("searchMap")Map<String, Object> searchMap);

	List<TbDepartmentInfoPO> getDepartmentList(@Param("searchMap")Map<String, Object> searchMap);

	List<TbUserInfoPO> getUserList(@Param("searchMap")Map<String, Object> searchMap);
}
