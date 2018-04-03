package cn.edu.zhku.jsj.huangxin.component.course.model;

import cn.edu.zhku.jsj.huangxin.component.base.model.IBasePO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TbCourseTerm implements IBasePO {

	private String id;
	private String termYear;
	private String term;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	private Integer weekNum;
	private Date createTime;
	private Date updateTime;
	private String creator;

	@Override
	public String _getTableName() {
		return "tb_course_term";
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

	public String getTermYear() {
		return termYear;
	}

	public void setTermYear(String termYear) {
		this.termYear = termYear;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getWeekNum() {
		return weekNum;
	}

	public void setWeekNum(Integer weekNum) {
		this.weekNum = weekNum;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
}
