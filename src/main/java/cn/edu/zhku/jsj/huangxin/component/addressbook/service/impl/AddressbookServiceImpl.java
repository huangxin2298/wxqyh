package cn.edu.zhku.jsj.huangxin.component.addressbook.service.impl;

import cn.edu.zhku.jsj.huangxin.component.addressbook.dao.AddressbookDAO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AccessTokenPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AdminUserPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.AgentPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbDepartmentInfoPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.service.IAddressbookService;
import cn.edu.zhku.jsj.huangxin.component.base.service.impl.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("addressbookService")
public class AddressbookServiceImpl extends BaseService implements IAddressbookService {

	private final static transient Logger logger = LoggerFactory.getLogger(AddressbookServiceImpl.class);
	private AddressbookDAO addressbookDAO;

	public AddressbookDAO getAddressbookDAO() {
		return addressbookDAO;
	}
	@Resource
	public void setAddressbookDAO(AddressbookDAO addressbookDAO) {
		this.addressbookDAO = addressbookDAO;
	}

	@Override
	public List<AccessTokenPO> getAccessTokenPO(Map<String, Object> searchMap) {
		return addressbookDAO.getAccessTokenPO(searchMap);
	}

	@Override
	public AgentPO getAgentPO(String agentCode) {
		return addressbookDAO.getAgentPO(agentCode);
	}

	@Override
	public AdminUserPO getAdminUser(Map<String, Object> searchMap) {
		return addressbookDAO.getAdminUser(searchMap);
	}

	@Override
	public TbDepartmentInfoPO getDepartmentInfo(Map<String, Object> searchMap) {
		return addressbookDAO.getDepartmentInfo(searchMap);
	}
}
