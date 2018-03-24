package cn.edu.zhku.jsj.huangxin.component.addressbook.ui;

import cn.edu.zhku.jsj.huangxin.component.addressbook.service.IAddressbookService;
import cn.edu.zhku.jsj.huangxin.component.addressbook.thread.SyncDeptAndUserThread;
import cn.edu.zhku.jsj.huangxin.component.base.model.ResultVO;
import cn.edu.zhku.jsj.huangxin.component.base.util.AssertUtils;
import cn.edu.zhku.jsj.huangxin.component.base.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/addressbook/*")
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
}
