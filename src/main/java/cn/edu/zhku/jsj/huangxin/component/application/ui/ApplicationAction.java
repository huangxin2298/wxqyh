package cn.edu.zhku.jsj.huangxin.component.application.ui;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbAdminUserPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbDepartmentInfoPO;
import cn.edu.zhku.jsj.huangxin.component.admin.model.TbMenuInfo;
import cn.edu.zhku.jsj.huangxin.component.application.service.IApplicationService;
import cn.edu.zhku.jsj.huangxin.component.base.model.ResultVO;
import cn.edu.zhku.jsj.huangxin.component.base.util.AssertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager/application/*")
public class ApplicationAction {

	private final static transient Logger logger = LoggerFactory.getLogger(ApplicationAction.class);

	private IApplicationService applicationService;

	@Resource(name="applicationService")
	public void setApplicationService(IApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@ResponseBody
	@RequestMapping("/getMenuList.action")
	public ResultVO getMenuList(HttpSession session) {
		ResultVO<List<TbMenuInfo>> resultVO = new ResultVO("查询成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			Map<String, Object> searchMap = new HashMap<>();
			searchMap.put("status","1");
			List<TbMenuInfo> menuList = applicationService.getMenuList(searchMap);
			resultVO.setData(menuList);
		}
		return resultVO;
	}
}
