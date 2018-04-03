package cn.edu.zhku.jsj.huangxin.component.application.service.impl;

import cn.edu.zhku.jsj.huangxin.component.admin.model.TbMenuInfo;
import cn.edu.zhku.jsj.huangxin.component.application.dao.ApplicationDAO;
import cn.edu.zhku.jsj.huangxin.component.application.service.IApplicationService;
import cn.edu.zhku.jsj.huangxin.component.base.service.impl.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("applicationService")
public class ApplicationServiceImpl extends BaseService implements IApplicationService {

	private final static transient Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);

	private ApplicationDAO applicationDAO;

	@Resource
	public void setApplicationDAO(ApplicationDAO applicationDAO) {
		this.applicationDAO = applicationDAO;
	}

	@Override
	public List<TbMenuInfo> getMenuList(Map<String, Object> searchMap) {
		return applicationDAO.getMenuList(searchMap);
	}
}
