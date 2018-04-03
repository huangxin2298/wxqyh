package cn.edu.zhku.jsj.huangxin.component.addressbook.thread;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbDepartmentInfoPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbUserInfoPO;
import cn.edu.zhku.jsj.huangxin.component.addressbook.service.IAddressbookService;
import cn.edu.zhku.jsj.huangxin.component.addressbook.util.AddressbookUtil;
import cn.edu.zhku.jsj.huangxin.component.base.model.*;
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
			//获取微信部门列表
			HttpResult httpResult = WeiXinUtils.getDeptList(accessToken,null);
			logger.error("------获取微信部门列表结果"+httpResult.toString()+"--------");
			if(httpResult.getCode() == 200){
				WeiXinDeptResult weiXinDeptResult = JSONObject.parseObject(httpResult.getContent(), WeiXinDeptResult.class);
				List<WeiXinDept> weiXinDeptList = weiXinDeptResult.getDepartment();
				List<TbDepartmentInfoPO> addDeptList = new ArrayList<>();
				List<TbUserInfoPO> addUserList = new ArrayList<>();
				Map<String, Object> deptMap = new HashMap<>();
				for(WeiXinDept weiXinDept : weiXinDeptList){
					deptMap.put(weiXinDept.getId(),weiXinDept);
				}
				while(weiXinDeptList.size() > 0){
					List<WeiXinDept> delList = new ArrayList<>();
					for(WeiXinDept weiXinDept : weiXinDeptList){
						if(!deptMap.containsKey(weiXinDept.getParentid())){
							logger.error("-------正在同步部门"+weiXinDept.toString()+"-------");
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
									logger.error("-------同步部门"+weiXinDept.getName()+"失败，父部门"+weiXinDept.getParentid()+"不存在！对应的微信id为"+weiXinDept.getId()+"------");
									continue;
								}
								deptFullName = parentDept.getDeptFullName() + "-->" + deptFullName;
							}else{
								parentDept = new TbDepartmentInfoPO();
							}

							if(!AssertUtils.isEmpty(tbDepartmentInfoPO)){
								tbDepartmentInfoPO.setDeptFullName(deptFullName);
								tbDepartmentInfoPO.setDepartmentName(weiXinDept.getName());
								if(!"0".equals(weiXinDept.getParentid())) {
									tbDepartmentInfoPO.setParentDepart(parentDept.getId());
									tbDepartmentInfoPO.setParentDeptName(parentDept.getDepartmentName());
								}else{
									tbDepartmentInfoPO.setParentDepart("0");
								}
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
								}else{
									tbDepartmentInfoPO.setParentDepart("0");
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
							//获取部门成员详情
							HttpResult userListResult = WeiXinUtils.getUserList(accessToken,weiXinDept.getId(),"0");
							logger.error("------获取部门成员详情结果"+userListResult.toString()+"--------");
							if(userListResult.getCode() == 200){
								WeiXinUserListResult weiXinUserListResult = JSONObject.parseObject(userListResult.getContent(), WeiXinUserListResult.class);
								List<WeiXinUser> weiXinUserList = weiXinUserListResult.getUserlist();
								if(!AssertUtils.isEmpty(weiXinUserList)){

									for(WeiXinUser weiXinUser : weiXinUserList){
										logger.error("------正在同步用户"+weiXinUser.toString()+"-------");
										Map<String, Object> userSearchMap = new HashMap<>();
										userSearchMap.put("user_id",weiXinUser.getUserid());
										TbUserInfoPO userInfo = addressbookService.getUserInfo(userSearchMap);
										if(!AssertUtils.isEmpty(userInfo)){
											userInfo.setUserName(weiXinUser.getName());
											userInfo.setEnglishName(weiXinUser.getEnglish_name());
											userInfo.setDeptId(tbDepartmentInfoPO.getId());
											userInfo.setDeptName(tbDepartmentInfoPO.getDepartmentName());
											userInfo.setShowOrder(weiXinUser.getOrder());
											userInfo.setPosition(weiXinUser.getPosition());
											userInfo.setMobile(weiXinUser.getMobile());
											userInfo.setSex(weiXinUser.getGender());
											userInfo.setEmail(weiXinUser.getEmail());
											userInfo.setIsLeader(weiXinUser.getIsleader());
											userInfo.setHeadPic(weiXinUser.getAvatar());
											userInfo.setTelephone(weiXinUser.getTelephone());
											userInfo.setStatus(weiXinUser.getStatus());
											userInfo.setOrgId(AddressbookUtil.getAdminOrgId());
											userInfo.setUpdateTime(new Date());
											addressbookService.updatePO(userInfo);
										}else{
											userInfo = new TbUserInfoPO();
											userInfo.setId(UUID.randomUUID().toString());
											userInfo.setUserId(weiXinUser.getUserid());
											userInfo.setUserName(weiXinUser.getName());
											userInfo.setEnglishName(weiXinUser.getEnglish_name());
											userInfo.setDeptId(tbDepartmentInfoPO.getId());
											userInfo.setDeptName(tbDepartmentInfoPO.getDepartmentName());
											userInfo.setShowOrder(weiXinUser.getOrder());
											userInfo.setPosition(weiXinUser.getPosition());
											userInfo.setMobile(weiXinUser.getMobile());
											userInfo.setSex(weiXinUser.getGender());
											userInfo.setEmail(weiXinUser.getEmail());
											userInfo.setCreator("admin");
											userInfo.setCreateTime(new Date());
											userInfo.setIsLeader(weiXinUser.getIsleader());
											userInfo.setHeadPic(weiXinUser.getAvatar());
											userInfo.setTelephone(weiXinUser.getTelephone());
											userInfo.setStatus(weiXinUser.getStatus());
											userInfo.setOrgId(AddressbookUtil.getAdminOrgId());
											addUserList.add(userInfo);
										}
									}

								}
							}
						}
					}
					if(!AssertUtils.isEmpty(delList)){
						weiXinDeptList.removeAll(delList);
					}
					if(!AssertUtils.isEmpty(addDeptList)){
						addressbookService.batchInsertPO(addDeptList);
						addDeptList.clear();
					}
				}
				if(!AssertUtils.isEmpty(addUserList)){
					addressbookService.batchInsertPO(addUserList);
				}
				RedisUtils.del("SyncDeptAndUserThread");
			}
		}
	}
}
