package cn.edu.zhku.jsj.huangxin.component.base.model;

import java.util.List;

public class WeiXinUserListResult {

	private String errcode;
	private String errmsg;
	private List<WeiXinUser> userlist;

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

	public List<WeiXinUser> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<WeiXinUser> userlist) {
		this.userlist = userlist;
	}

	@Override
	public String toString() {
		return "WeiXinUserListResult{" +
				"errcode='" + errcode + '\'' +
				", errmsg='" + errmsg + '\'' +
				", userlist=" + userlist +
				'}';
	}
}
