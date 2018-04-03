package cn.edu.zhku.jsj.huangxin.component.application.service;

import cn.edu.zhku.jsj.huangxin.component.admin.model.TbMenuInfo;
import cn.edu.zhku.jsj.huangxin.component.base.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface IApplicationService extends IBaseService {

	/**
	 * 获取菜单列表
	 * @param searchMap
	 * @return
	 */
	List<TbMenuInfo> getMenuList(Map<String, Object> searchMap);
}
