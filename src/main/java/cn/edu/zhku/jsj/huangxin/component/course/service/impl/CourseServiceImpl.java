package cn.edu.zhku.jsj.huangxin.component.course.service.impl;

import cn.edu.zhku.jsj.huangxin.component.base.model.Page;
import cn.edu.zhku.jsj.huangxin.component.base.service.impl.BaseService;
import cn.edu.zhku.jsj.huangxin.component.course.dao.CourseDAO;
import cn.edu.zhku.jsj.huangxin.component.course.model.TbCourseClassroom;
import cn.edu.zhku.jsj.huangxin.component.course.model.TbCourseInfo;
import cn.edu.zhku.jsj.huangxin.component.course.model.TbCourseTerm;
import cn.edu.zhku.jsj.huangxin.component.course.service.ICourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("courseService")
public class CourseServiceImpl extends BaseService implements ICourseService {

	private final static transient Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	private CourseDAO courseDAO;
	@Resource
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	@Override
	public void getTermPage(Map<String, Object> searchMap, Page<TbCourseTerm> page) {
		PageHelper.startPage(page.getCurPage(), page.getPageSize());
		List<TbCourseTerm> list;
		list = courseDAO.getTermPage(searchMap);
		PageInfo<TbCourseTerm> pageInfo = new PageInfo<>(list);
		page.setData(list);
		page.setTotalPages(pageInfo.getPages());
		page.setTotalRows((int) pageInfo.getTotal());
	}

	@Override
	public List<TbCourseTerm> getCourseTermList(Map<String, Object> searchMap) {
		return courseDAO.getCourseTermList(searchMap);
	}

	@Override
	public TbCourseTerm getCourseTermInfo(Map<String, Object> searchMap) {
		return courseDAO.getCourseTermInfo(searchMap);
	}

	@Override
	public void getClassroomPage(Map<String, Object> searchMap, Page<TbCourseClassroom> page) {
		PageHelper.startPage(page.getCurPage(), page.getPageSize());
		List<TbCourseClassroom> list;
		list = courseDAO.getClassroomPage(searchMap);
		PageInfo<TbCourseClassroom> pageInfo = new PageInfo<>(list);
		page.setData(list);
		page.setTotalPages(pageInfo.getPages());
		page.setTotalRows((int) pageInfo.getTotal());
	}

	@Override
	public TbCourseClassroom getCourseClassroomInfo(Map<String, Object> searchMap) {
		return courseDAO.getCourseClassroomInfo(searchMap);
	}

	@Override
	public TbCourseTerm getCourseTermByTime(Date startTime, Date endTime) {
		return courseDAO.getCourseTermByTime(startTime, endTime);
	}

	@Override
	public void getCoursePage(Map<String, Object> searchMap, Page<TbCourseInfo> page) {
		PageHelper.startPage(page.getCurPage(), page.getPageSize());
		List<TbCourseInfo> list;
		list = courseDAO.getCoursePage(searchMap);
		PageInfo<TbCourseInfo> pageInfo = new PageInfo<>(list);
		page.setData(list);
		page.setTotalPages(pageInfo.getPages());
		page.setTotalRows((int) pageInfo.getTotal());
	}

	@Override
	public void delCourseById(String courseId) {
		courseDAO.delArrangeByCoursId(courseId);
		deletePO(TbCourseInfo.class,courseId);
	}
}
