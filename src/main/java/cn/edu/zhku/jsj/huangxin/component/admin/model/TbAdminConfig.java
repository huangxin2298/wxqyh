package cn.edu.zhku.jsj.huangxin.component.admin.model;

import cn.edu.zhku.jsj.huangxin.component.base.model.IBasePO;

import java.util.Date;

public class TbAdminConfig implements IBasePO{
	private String id;
	private String configName;
	private String configValue;
	private String description;
	private Date createTime;
	private Date updateTime;
	@Override
	public String _getTableName() {
		return "tb_admin_config";
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

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
