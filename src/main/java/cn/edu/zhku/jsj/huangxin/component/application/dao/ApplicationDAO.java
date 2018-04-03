package cn.edu.zhku.jsj.huangxin.component.application.dao;

import cn.edu.zhku.jsj.huangxin.component.admin.model.TbMenuInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ApplicationDAO {

	/**
	 * 获取菜单列表
	 * @param searchMap
	 * @return
	 */
	List<TbMenuInfo> getMenuList(@Param("searchMap") Map<String, Object> searchMap);
}
