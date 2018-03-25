package cn.edu.zhku.jsj.huangxin.component.addressbook.ui;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbAdminUserPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbDepartmentInfoPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbUserInfoPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.service.IAddressbookService;
import cn.edu.zhku.jsj.huangxin.component.addressbook.thread.SyncDeptAndUserThread;
import cn.edu.zhku.jsj.huangxin.component.base.model.Page;
import cn.edu.zhku.jsj.huangxin.component.base.model.ResultVO;
import cn.edu.zhku.jsj.huangxin.component.base.util.AssertUtils;
import cn.edu.zhku.jsj.huangxin.component.base.util.MD5Util;
import cn.edu.zhku.jsj.huangxin.component.base.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager/addressbook/*")
public class AddressbookAction {

	private final static transient Logger logger = LoggerFactory.getLogger(AddressbookAction.class);

	private IAddressbookService addressbookService;

	public IAddressbookService getAddressbookService() {
		return addressbookService;
	}

	@Resource(name = "addressbookService")
	public void setAddressbookService(IAddressbookService addressbookService) {
		this.addressbookService = addressbookService;
	}

	@ResponseBody
	@RequestMapping("/syncDeptAndUser.action")
	public ResultVO syncDeptAndUser() {
		ResultVO resultVO = new ResultVO("后台正在微信同步数据到服务器");
		String rs = (String)RedisUtils.get("SyncDeptAndUserThread");
		if("1".equals(rs)){
			resultVO.setCode("999");
			resultVO.setDescribe("后台已存在同步线程，请稍后重试");
		}else{
			RedisUtils.set("SyncDeptAndUserThread","1",120);
			SyncDeptAndUserThread thread = new SyncDeptAndUserThread();
			thread.start();
		}
		return resultVO;
	}
	@ResponseBody
	@RequestMapping("/authUser.action")
	public ResultVO authUser(@RequestParam("userName")String userName, @RequestParam("password") String password, HttpSession session) {
		ResultVO resultVO = new ResultVO("登录成功");
		if(AssertUtils.isEmpty(userName)){
			resultVO.setCode("999");
			resultVO.setDescribe("登录失败，用户名为空");
		}else if(AssertUtils.isEmpty(password)){
			resultVO.setCode("999");
			resultVO.setDescribe("登录失败，密码为空");
		}else{
			Map<String, Object> searchMap = new HashMap<>();
			searchMap.put("user_name",userName);
			searchMap.put("password", MD5Util.MD5(password));
			TbAdminUserPO adminUser = addressbookService.getAdminUserInfo(searchMap);
			if(AssertUtils.isEmpty(adminUser)){
				resultVO.setCode("1099");
				resultVO.setDescribe("登录失败，用户名或密码错误");
			}else{
				session.setAttribute("adminUser",adminUser);
			}
		}
		return resultVO;
	}
	@ResponseBody
	@RequestMapping("/loginOutAdminUser.action")
	public ResultVO loginOutAdminUser(HttpSession session) {
		ResultVO resultVO = new ResultVO("退出成功");
		session.removeAttribute("adminUser");
		return resultVO;
	}
	@ResponseBody
	@RequestMapping("/getDepartmentList.action")
	public ResultVO getDepartmentList(HttpSession session) {
		ResultVO<List<TbDepartmentInfoPO>> resultVO = new ResultVO("查询成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if(AssertUtils.isEmpty(adminUser)){
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		}else{
			Map<String, Object> searchMap = new HashMap<>();
			searchMap.put("org_id",adminUser.getOrgId());
			List<TbDepartmentInfoPO> departmentInfoPOList = addressbookService.getDepartmentList(searchMap);
			resultVO.setData(departmentInfoPOList);
		}
		return resultVO;
	}
	@ResponseBody
	@RequestMapping("/getUserPageByDeptId.action")
	public ResultVO getUserPageByDeptId(@RequestParam("deptId")String deptId,@RequestParam("inputSearch")String inputSearch,@RequestParam
			(value = "curPage", required = false) String curPage, HttpSession session) {
		ResultVO<Page<TbUserInfoPO>> resultVO = new ResultVO("查询成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if(AssertUtils.isEmpty(adminUser)){
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		}else{

			Page<TbUserInfoPO> page = new Page<TbUserInfoPO>();
			if (curPage == null || curPage == "") {
				curPage = "1";
			}
			page.setCurPage(Integer.parseInt(curPage));
			page.setPageSize(10);

			Map<String, Object> searchMap = new HashMap<>();
			searchMap.put("org_id",adminUser.getOrgId());
			if(!AssertUtils.isEmpty(deptId)){
				searchMap.put("dept_id",deptId);
			}
			if(!AssertUtils.isEmpty(inputSearch)){
				searchMap.put("inputSearch","%"+inputSearch+"%");
			}
			addressbookService.getUserList(searchMap, page);
			resultVO.setData(page);
		}
		return resultVO;
	}
}
