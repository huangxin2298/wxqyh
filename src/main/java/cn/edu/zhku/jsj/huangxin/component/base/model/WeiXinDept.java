package cn.edu.zhku.jsj.huangxin.component.base.model;

public class WeiXinDept {

	private String id;
	private String name;
	private String parentid;
	private Integer order;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "WeiXinDept{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", parentid='" + parentid + '\'' +
				", order=" + order +
				'}';
	}
}
