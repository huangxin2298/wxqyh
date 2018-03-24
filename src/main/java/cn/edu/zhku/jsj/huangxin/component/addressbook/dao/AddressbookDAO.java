package cn.edu.zhku.jsj.huangxin.component.addressbook.dao;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AccessTokenPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AdminUserPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AgentPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbDepartmentInfoPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AddressbookDAO {

	List<AccessTokenPO> getAccessTokenPO(@Param("searchMap") Map<String,Object> searchMap);

	AgentPO getAgentPO(@Param("agentCode") String agentCode);

	AdminUserPO getAdminUser(@Param("searchMap")Map<String, Object> searchMap);

	TbDepartmentInfoPO getDepartmentInfo(@Param("searchMap")Map<String, Object> searchMap);
}
