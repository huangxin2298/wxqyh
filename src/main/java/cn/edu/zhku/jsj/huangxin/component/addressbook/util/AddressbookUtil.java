package cn.edu.zhku.jsj.huangxin.component.addressbook.util;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AdminUserPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbDepartmentInfoPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.service.IAddressbookService;
import cn.edu.zhku.jsj.huangxin.component.base.util.AssertUtils;
import cn.edu.zhku.jsj.huangxin.component.base.util.SpringContextHolder;

import java.util.HashMap;
import java.util.Map;

public class AddressbookUtil {

	private static IAddressbookService addressbookService = SpringContextHolder.getBean(IAddressbookService.class);

	/**
	 * 获取默认的admin帐号的org_id
	 * @return
	 */
	public static String getAdminOrgId(){
		AdminUserPO adminUserPO = addressbookService.searchPOByPk(AdminUserPO.class,"admin");
		return adminUserPO.getOrgId();
	}

	/**
	 * 通过部门wx_id获取部门全称
	 * @param wxId
	 * @return
	 */
	public static String getDeptFullName(String wxId){
		String deptFullName = null;
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("wx_id",wxId);
		TbDepartmentInfoPO departmentInfoPO = addressbookService.getDepartmentInfo(searchMap);
		if(!AssertUtils.isEmpty(departmentInfoPO)){
			deptFullName = departmentInfoPO.getDeptFullName();
		}
		return deptFullName;
	}
}
