package cn.edu.zhku.jsj.huangxin.component.course.service;

import cn.edu.zhku.jsj.huangxin.component.base.model.Page;
import cn.edu.zhku.jsj.huangxin.component.base.service.IBaseService;
import cn.edu.zhku.jsj.huangxin.component.course.model.TbCourseClassroom;
import cn.edu.zhku.jsj.huangxin.component.course.model.TbCourseInfo;
import cn.edu.zhku.jsj.huangxin.component.course.model.TbCourseTerm;
import cn.edu.zhku.jsj.huangxin.component.course.vo.CourseInfoVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ICourseService extends IBaseService {
	/**
	 * 获取学期列表
	 * @param searchMap
	 * @param page
	 */
	void getTermPage(Map<String, Object> searchMap, Page<TbCourseTerm> page);

	/**
	 * 获取学期列表
	 * @param searchMap
	 * @return
	 */
	List<TbCourseTerm> getCourseTermList(Map<String, Object> searchMap);

	/**
	 * 获取学期信息
	 * @param searchMap
	 * @return
	 */
	TbCourseTerm getCourseTermInfo(Map<String, Object> searchMap);

	/**
	 * 获取课室列表
	 * @param searchMap
	 * @param page
	 */
	void getClassroomPage(Map<String, Object> searchMap, Page<TbCourseClassroom> page);

	/**
	 * 获取课室信息
	 * @param searchMap
	 * @return
	 */
	TbCourseClassroom getCourseClassroomInfo(Map<String, Object> searchMap);

	/**
	 * 获取在指定时间段的学期
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	TbCourseTerm getCourseTermByTime(Date startTime, Date endTime);

	/**
	 * 获取课程列表
	 * @param searchMap
	 * @param page
	 */
	void getCoursePage(Map<String, Object> searchMap, Page<TbCourseInfo> page);

	/**
	 * 删除课程
	 * @param courseId
	 */
	void delCourseById(String courseId);
}
