package cn.edu.zhku.jsj.huangxin.component.course.dao;

import cn.edu.zhku.jsj.huangxin.component.course.model.TbCourseClassroom;
import cn.edu.zhku.jsj.huangxin.component.course.model.TbCourseInfo;
import cn.edu.zhku.jsj.huangxin.component.course.model.TbCourseTerm;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CourseDAO {

	/**
	 * 获取学期列表
	 * @param searchMap
	 * @return
	 */
	List<TbCourseTerm> getTermPage(@Param("searchMap") Map<String, Object> searchMap);

	/**
	 * 获取学期列表
	 * @param searchMap
	 * @return
	 */
	List<TbCourseTerm> getCourseTermList(@Param("searchMap")Map<String, Object> searchMap);

	/**
	 * 获取学期信息
	 * @param searchMap
	 * @return
	 */
	TbCourseTerm getCourseTermInfo(@Param("searchMap") Map<String, Object> searchMap);

	/**
	 * 获取课室列表
	 * @param searchMap
	 * @return
	 */
	List<TbCourseClassroom> getClassroomPage(@Param("searchMap") Map<String, Object> searchMap);

	/**
	 * 获取课室信息
	 * @param searchMap
	 * @return
	 */
	TbCourseClassroom getCourseClassroomInfo(@Param("searchMap")Map<String, Object> searchMap);

	/**
	 * 获取在指定时间段的学期
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	TbCourseTerm getCourseTermByTime(@Param("startTime") Date startTime,@Param("endTime")  Date endTime);

	/**
	 * 获取课程列表
	 * @param searchMap
	 * @return
	 */
	List<TbCourseInfo> getCoursePage(@Param("searchMap")Map<String, Object> searchMap);

	/**
	 * 根据课程id删除课程安排
	 * @param courseId
	 */
	void delArrangeByCoursId(@Param("courseId")String courseId);


}
