package cn.edu.zhku.jsj.huangxin.component.base.model;

public class WeiXinUser {

	private String userid;
	private String name;
	private String department;
	private String order;
	private String position;
	private String mobile;
	private String gender;
	private String email;
	private Integer isleader;
	private String avatar;
	private String telephone;
	private String english_name;
	private Integer status;
	private String extattr;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsleader() {
		return isleader;
	}

	public void setIsleader(Integer isleader) {
		this.isleader = isleader;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEnglish_name() {
		return english_name;
	}

	public void setEnglish_name(String english_name) {
		this.english_name = english_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExtattr() {
		return extattr;
	}

	public void setExtattr(String extattr) {
		this.extattr = extattr;
	}

	@Override
	public String toString() {
		return "WeiXinUser{" +
				"userid='" + userid + '\'' +
				", name='" + name + '\'' +
				", department='" + department + '\'' +
				", order='" + order + '\'' +
				", position='" + position + '\'' +
				", mobile='" + mobile + '\'' +
				", gender='" + gender + '\'' +
				", email='" + email + '\'' +
				", isleader=" + isleader +
				", avatar='" + avatar + '\'' +
				", telephone='" + telephone + '\'' +
				", english_name='" + english_name + '\'' +
				", status=" + status +
				", extattr='" + extattr + '\'' +
				'}';
	}
}
