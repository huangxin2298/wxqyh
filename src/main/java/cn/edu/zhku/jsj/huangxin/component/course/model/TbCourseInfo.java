package cn.edu.zhku.jsj.huangxin.component.course.model;

import cn.edu.zhku.jsj.huangxin.component.base.model.IBasePO;

import java.util.Date;

public class TbCourseInfo implements IBasePO {

	private String id;
	private String courseName;
	private String termId;
	private String termName;
	private Integer startWeek;
	private Integer endWeek;
	private Integer classTime;
	private String sessions;
	private String classroomId;
	private String classroomName;
	private String teacher;
	private Date createTime;
	private Date updateTime;
	private Date creator;

	@Override
	public String _getTableName() {
		return "tb_course_info";
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public Integer getStartWeek() {
		return startWeek;
	}

	public void setStartWeek(Integer startWeek) {
		this.startWeek = startWeek;
	}

	public Integer getEndWeek() {
		return endWeek;
	}

	public void setEndWeek(Integer endWeek) {
		this.endWeek = endWeek;
	}

	public Integer getClassTime() {
		return classTime;
	}

	public void setClassTime(Integer classTime) {
		this.classTime = classTime;
	}

	public String getSessions() {
		return sessions;
	}

	public void setSessions(String sessions) {
		this.sessions = sessions;
	}

	public String getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(String classroomId) {
		this.classroomId = classroomId;
	}

	public String getClassroomName() {
		return classroomName;
	}

	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
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

	public Date getCreator() {
		return creator;
	}

	public void setCreator(Date creator) {
		this.creator = creator;
	}
}
