package cn.edu.zhku.jsj.huangxin.component.addressbook.model;

import cn.edu.zhku.jsj.huangxin.component.base.model.IBasePO;

import java.util.Date;

public class AgentPO implements IBasePO{

	private String id;
	private String agentName;
	private String agentCode;
	private String secret;
	private Integer status;
	private Date createTime;
	private Date updateTime;

	@Override
	public String _getTableName() {
		return "tb_agent_info";
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
