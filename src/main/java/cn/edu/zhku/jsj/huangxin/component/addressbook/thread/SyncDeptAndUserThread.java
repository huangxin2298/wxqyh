package cn.edu.zhku.jsj.huangxin.component.addressbook.thread;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbDepartmentInfoPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.service.IAddressbookService;
import cn.edu.zhku.jsj.huangxin.component.addressbook.util.AddressbookUtil;
import cn.edu.zhku.jsj.huangxin.component.base.model.HttpResult;
import cn.edu.zhku.jsj.huangxin.component.base.model.WeiXinDept;
import cn.edu.zhku.jsj.huangxin.component.base.model.WeiXinDeptResult;
import cn.edu.zhku.jsj.huangxin.component.base.util.AssertUtils;
import cn.edu.zhku.jsj.huangxin.component.base.util.RedisUtils;
import cn.edu.zhku.jsj.huangxin.component.base.util.SpringContextHolder;
import cn.edu.zhku.jsj.huangxin.component.base.util.WeiXinUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class SyncDeptAndUserThread extends Thread {

	private final static transient Logger logger = LoggerFactory.getLogger(SyncDeptAndUserThread.class);
	private IAddressbookService addressbookService = SpringContextHolder.getBean(IAddressbookService.class);

	@Override
	public void run() {
		logger.error("------开始从微信端同步数据到服务器------");
		String orgId = AddressbookUtil.getAdminOrgId();
		String accessToken = WeiXinUtils.getAccessToken("org",orgId);
		if(!AssertUtils.isEmpty(accessToken)){
			HttpResult httpResult = WeiXinUtils.getDeptList(accessToken,null);
			logger.error(httpResult.toString());
			if(httpResult.getCode() == 200){
				WeiXinDeptResult weiXinDeptResult = JSONObject.parseObject(httpResult.getContent(), WeiXinDeptResult.class);
				List<WeiXinDept> weiXinDeptList = weiXinDeptResult.getDepartment();
				List<TbDepartmentInfoPO> addDeptList = new ArrayList<>();
				Map<String, Object> deptMap = new HashMap<>();
				for(WeiXinDept weiXinDept : weiXinDeptList){
					deptMap.put(weiXinDept.getId(),weiXinDept);
				}
				while(weiXinDeptList.size() > 0){
					List<WeiXinDept> delList = new ArrayList<>();
					for(WeiXinDept weiXinDept : weiXinDeptList){
						if(!deptMap.containsKey(weiXinDept.getParentid())){
							logger.error(weiXinDept.toString());
							Map<String, Object> searchMap = new HashMap<>();
							searchMap.put("wx_id",weiXinDept.getId());
							TbDepartmentInfoPO tbDepartmentInfoPO = addressbookService.getDepartmentInfo(searchMap);
							Map<String, Object> searchpParentMap = new HashMap<>();
							TbDepartmentInfoPO parentDept = null;
							String deptFullName = weiXinDept.getName();
							if(!"0".equals(weiXinDept.getParentid())){
								searchpParentMap.put("wx_id",weiXinDept.getParentid());
								parentDept= addressbookService.getDepartmentInfo(searchpParentMap);
								if(AssertUtils.isEmpty(parentDept)){
									logger.error("同步部门"+weiXinDept.getName()+"失败，父部门"+weiXinDept.getParentid()+"不存在！对应的微信id为"+weiXinDept.getId());
									continue;
								}
								deptFullName = parentDept.getDeptFullName() + "-->" + deptFullName;
							}

							if(!AssertUtils.isEmpty(tbDepartmentInfoPO)){
								tbDepartmentInfoPO.setDeptFullName(deptFullName);
								tbDepartmentInfoPO.setDepartmentName(weiXinDept.getName());
								tbDepartmentInfoPO.setWxParentid(weiXinDept.getParentid());
								tbDepartmentInfoPO.setShowOrder(weiXinDept.getOrder());
								tbDepartmentInfoPO.setUpdateTime(new Date());
								addressbookService.updatePO(tbDepartmentInfoPO);
								delList.add(weiXinDept);
								deptMap.remove(weiXinDept.getId());
							}else{
								tbDepartmentInfoPO = new TbDepartmentInfoPO();
								tbDepartmentInfoPO.setId(UUID.randomUUID().toString());
								tbDepartmentInfoPO.setDepartmentName(weiXinDept.getName());
								if(!"0".equals(weiXinDept.getParentid())) {
									tbDepartmentInfoPO.setParentDepart(parentDept.getId());
								}
								tbDepartmentInfoPO.setCreator("admin");
								tbDepartmentInfoPO.setCreateTime(new Date());
								tbDepartmentInfoPO.setUpdateTime(new Date());
								tbDepartmentInfoPO.setOrgId(AddressbookUtil.getAdminOrgId());
								tbDepartmentInfoPO.setDeptFullName(deptFullName);
								tbDepartmentInfoPO.setShowOrder(weiXinDept.getOrder());
								tbDepartmentInfoPO.setWxId(weiXinDept.getId());
								tbDepartmentInfoPO.setWxParentid(weiXinDept.getParentid());
								addDeptList.add(tbDepartmentInfoPO);
								delList.add(weiXinDept);
								deptMap.remove(weiXinDept.getId());
							}
						}
					}
					if(!AssertUtils.isEmpty(delList)){
						weiXinDeptList.removeAll(delList);
					}
				}
				if(!AssertUtils.isEmpty(addDeptList)){
					addressbookService.batchInsertPO(addDeptList);
				}
				RedisUtils.del("SyncDeptAndUserThread");
			}
		}
	}
}
