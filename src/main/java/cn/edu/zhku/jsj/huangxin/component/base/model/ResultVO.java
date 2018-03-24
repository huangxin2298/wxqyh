package cn.edu.zhku.jsj.huangxin.component.base.model;

public class ResultVO<T> {
	private String code;//结果返回状态码，为0表示正常
	private String describe;//结果描述
	private T data;//结果数据

	public ResultVO() {
	}

	public ResultVO(String describe) {
		this.code = "0";
		this.describe = describe;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
