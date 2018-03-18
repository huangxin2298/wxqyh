package cn.edu.zhku.jsj.huangxin.component.base.model;

import java.io.Serializable;

public class HttpResult implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer code;//状态码
    private String content;//内容
    public HttpResult() {
    }
    public HttpResult(Integer code, String content) {
        this.code = code;
        this.content = content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", content='" + content + '\'' +
                '}';
    }
}
