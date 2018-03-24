package cn.edu.zhku.jsj.huangxin.component.base.model;

import java.util.List;

public class WeiXinDeptResult {

	private String errcode;
	private String errmsg;
	private List<WeiXinDept> department;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public List<WeiXinDept> getDepartment() {
		return department;
	}

	public void setDepartment(List<WeiXinDept> department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "WeiXinDeptResult{" +
				"errcode='" + errcode + '\'' +
				", errmsg='" + errmsg + '\'' +
				", department=" + department +
				'}';
	}
}
