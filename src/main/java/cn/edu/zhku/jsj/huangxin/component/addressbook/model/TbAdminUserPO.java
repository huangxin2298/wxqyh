package cn.edu.zhku.jsj.huangxin.component.addressbook.model;

import cn.edu.zhku.jsj.huangxin.component.base.model.IBasePO;

import java.util.Date;

public class TbAdminUserPO implements IBasePO{

	private String id;
	private String userName;
	private String account;
	private String password;
	private Date createTime;
	private Date lastLoginTime;
	private String orgId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Override
	public String _getTableName() {
		return "tb_admin_user";
	}

	@Override
	public String _getPKName() {
		return "id";
	}

	@Override
	public Object _getPKValue() {
		return String.valueOf(id);
	}

	@Override
	public void _setPKValue(Object id) {
		this.id = (String) id;
	}
}
