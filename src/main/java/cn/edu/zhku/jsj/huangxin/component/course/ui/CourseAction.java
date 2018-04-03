package cn.edu.zhku.jsj.huangxin.component.course.ui;

import cn.edu.zhku.jsj.huangxin.component.addressbook.model.TbAdminUserPO;
import cn.edu.zhku.jsj.huangxin.component.base.model.Page;
import cn.edu.zhku.jsj.huangxin.component.base.model.ResultVO;
import cn.edu.zhku.jsj.huangxin.component.base.util.AssertUtils;
import cn.edu.zhku.jsj.huangxin.component.course.model.TbCourseClassroom;
import cn.edu.zhku.jsj.huangxin.component.course.model.TbCourseInfo;
import cn.edu.zhku.jsj.huangxin.component.course.model.TbCourseTerm;
import cn.edu.zhku.jsj.huangxin.component.course.service.ICourseService;
import cn.edu.zhku.jsj.huangxin.component.course.vo.CourseInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/manager/course/*")
public class CourseAction {

	private final static transient Logger logger = LoggerFactory.getLogger(CourseAction.class);

	private ICourseService courseService;
	@Resource(name="courseService")
	public void setCourseService(ICourseService courseService) {
		this.courseService = courseService;
	}

	@ResponseBody
	@RequestMapping("/getCourseTermInfo.action")
	public ResultVO getCourseTermInfo(String termId, HttpSession session) {
		ResultVO<TbCourseTerm> resultVO = new ResultVO("查询成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			if (AssertUtils.isEmpty(termId)) {
				resultVO.setCode("999");
				resultVO.setDescribe("非法参数");
			} else {
				TbCourseTerm courseTerm = courseService.searchPOByPk(TbCourseTerm.class, termId);
				if (AssertUtils.isEmpty(courseTerm)) {
					resultVO.setCode("1099");
					resultVO.setDescribe("获取学期信息失败，该学期不存在！");
				} else {
					resultVO.setData(courseTerm);
				}
			}
		}
		return resultVO;
	}

	@ResponseBody
	@RequestMapping("/getCourseTermList.action")
	public ResultVO getCourseTermList(HttpSession session) {
		ResultVO<List<TbCourseTerm>> resultVO = new ResultVO("查询成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			Map<String, Object> searchMap = new HashMap<>();
			List<TbCourseTerm> courseTermList = courseService.getCourseTermList(searchMap);
			resultVO.setData(courseTermList);
		}
		return resultVO;
	}

	@ResponseBody
	@RequestMapping("/getTermPage.action")
	public ResultVO getTermPage(@RequestParam("inputSearch") String inputSearch, @RequestParam
			(value = "curPage", required = false) String curPage, HttpSession session) {
		ResultVO<Page<TbCourseTerm>> resultVO = new ResultVO("查询成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			Page<TbCourseTerm> page = new Page<TbCourseTerm>();
			if (curPage == null || curPage == "") {
				curPage = "1";
			}
			page.setCurPage(Integer.parseInt(curPage));
			page.setPageSize(10);

			Map<String, Object> searchMap = new HashMap<>();
			if (!AssertUtils.isEmpty(inputSearch)) {
				searchMap.put("inputSearch", "%" + inputSearch + "%");
			}
			courseService.getTermPage(searchMap, page);
			resultVO.setData(page);
		}
		return resultVO;
	}

	@ResponseBody
	@RequestMapping("/addCourseTerm.action")
	public ResultVO addCourseTerm(TbCourseTerm tbCourseTerm, HttpSession session) {
		ResultVO<TbCourseTerm> resultVO = new ResultVO("添加成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			if (AssertUtils.isEmpty(tbCourseTerm)) {
				resultVO.setCode("999");
				resultVO.setDescribe("非法参数!");
			} else {
				Map<String, Object> searchMap = new HashMap<>();
				searchMap.put("term_year", tbCourseTerm.getTermYear());
				searchMap.put("term", tbCourseTerm.getTerm());
				TbCourseTerm courseTerm = courseService.getCourseTermInfo(searchMap);
				if (!AssertUtils.isEmpty(courseTerm)) {
					resultVO.setCode("1099");
					resultVO.setDescribe("添加学期失败，该学期已经存在!");
				} else {
					TbCourseTerm courseTerm2 =courseService.getCourseTermByTime(tbCourseTerm.getStartTime(),tbCourseTerm.getEndTime());
					if(!AssertUtils.isEmpty(courseTerm2)){
						resultVO.setCode("1099");
						resultVO.setDescribe("添加学期失败，两个学期时间不能重叠!");
					}else{
						tbCourseTerm.setId(UUID.randomUUID().toString());
						tbCourseTerm.setCreateTime(new Date());
						tbCourseTerm.setCreator(adminUser.getUserName());
						courseService.insertPO(tbCourseTerm);
					}
				}
			}
		}
		return resultVO;
	}

	@ResponseBody
	@RequestMapping("/delTermById.action")
	public ResultVO delTermById(String termId, HttpSession session) {
		ResultVO<TbCourseTerm> resultVO = new ResultVO("删除成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			if (AssertUtils.isEmpty(termId)) {
				resultVO.setCode("999");
				resultVO.setDescribe("非法参数");
			} else {
				TbCourseTerm courseTerm = courseService.searchPOByPk(TbCourseTerm.class, termId);
				if (AssertUtils.isEmpty(courseTerm)) {
					resultVO.setCode("1099");
					resultVO.setDescribe("删除失败，学期不存在！");
				} else {
					courseService.deletePO(TbCourseTerm.class, termId);
				}
			}
		}
		return resultVO;
	}

	@ResponseBody
	@RequestMapping("/getClassroomInfo.action")
	public ResultVO getClassroomInfo(String classroomId, HttpSession session) {
		ResultVO<TbCourseClassroom> resultVO = new ResultVO("查询成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			if (AssertUtils.isEmpty(classroomId)) {
				resultVO.setCode("999");
				resultVO.setDescribe("非法参数");
			} else {
				TbCourseClassroom courseClassroom = courseService.searchPOByPk(TbCourseClassroom.class, classroomId);
				if (AssertUtils.isEmpty(courseClassroom)) {
					resultVO.setCode("1099");
					resultVO.setDescribe("获取课室信息失败，该课室不存在！");
				} else {
					resultVO.setData(courseClassroom);
				}
			}
		}
		return resultVO;
	}

	@ResponseBody
	@RequestMapping("/getClassroomPage.action")
	public ResultVO getClassroomPage(@RequestParam("inputSearch") String inputSearch, @RequestParam
			(value = "curPage", required = false) String curPage, HttpSession session) {
		ResultVO<Page<TbCourseClassroom>> resultVO = new ResultVO("查询成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			Page<TbCourseClassroom> page = new Page<TbCourseClassroom>();
			if (curPage == null || curPage == "") {
				curPage = "1";
			}
			page.setCurPage(Integer.parseInt(curPage));
			page.setPageSize(10);

			Map<String, Object> searchMap = new HashMap<>();
			if (!AssertUtils.isEmpty(inputSearch)) {
				searchMap.put("inputSearch", "%" + inputSearch + "%");
			}
			courseService.getClassroomPage(searchMap, page);
			resultVO.setData(page);
		}
		return resultVO;
	}

	@ResponseBody
	@RequestMapping("/addCourseClassroom.action")
	public ResultVO addCourseClassroom(TbCourseClassroom tbCourseClassroom, HttpSession session) {
		ResultVO<TbCourseClassroom> resultVO = new ResultVO("添加成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			if (AssertUtils.isEmpty(tbCourseClassroom)) {
				resultVO.setCode("999");
				resultVO.setDescribe("非法参数");
			} else {
				Map<String, Object> searchMap = new HashMap<>();
				searchMap.put("classroom_name", tbCourseClassroom.getClassroomName());
				TbCourseClassroom courseClassroom = courseService.getCourseClassroomInfo(searchMap);
				if (!AssertUtils.isEmpty(courseClassroom)) {
					resultVO.setCode("1099");
					resultVO.setDescribe("添加课室失败，该课室名已经存在");
				} else {
					tbCourseClassroom.setId(UUID.randomUUID().toString());
					tbCourseClassroom.setCreateTime(new Date());
					tbCourseClassroom.setCreator(adminUser.getUserName());
					tbCourseClassroom.setStatus(1);
					courseService.insertPO(tbCourseClassroom);
				}
			}
		}
		return resultVO;
	}

	@ResponseBody
	@RequestMapping("/delClassroomById.action")
	public ResultVO delClassroomById(String classroomId, HttpSession session) {
		ResultVO<TbCourseClassroom> resultVO = new ResultVO("删除成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			if (AssertUtils.isEmpty(classroomId)) {
				resultVO.setCode("999");
				resultVO.setDescribe("非法参数");
			} else {
				TbCourseClassroom classroomInfo = courseService.searchPOByPk(TbCourseClassroom.class, classroomId);
				if (AssertUtils.isEmpty(classroomInfo)) {
					resultVO.setCode("1099");
					resultVO.setDescribe("删除失败，课室不存在！");
				} else {
					courseService.deletePO(TbCourseClassroom.class, classroomId);
				}
			}
		}
		return resultVO;
	}

	@ResponseBody
	@RequestMapping("/updateClassroomInfo.action")
	public ResultVO updateClassroomInfo(TbCourseClassroom tbCourseClassroom, HttpSession session) {
		ResultVO<TbCourseTerm> resultVO = new ResultVO("更新成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			if (AssertUtils.isEmpty(tbCourseClassroom) || AssertUtils.isEmpty(tbCourseClassroom.getId())) {
				resultVO.setCode("999");
				resultVO.setDescribe("非法参数");
			} else {
				TbCourseClassroom courseClassroom = courseService.searchPOByPk(TbCourseClassroom.class, tbCourseClassroom.getId());
				if (AssertUtils.isEmpty(courseClassroom)) {
					resultVO.setCode("1099");
					resultVO.setDescribe("更新失败，该课室不存在!");
				} else {
					tbCourseClassroom.setUpdateTime(new Date());
					courseService.updatePO(tbCourseClassroom);
				}
			}
		}
		return resultVO;
	}

	@ResponseBody
	@RequestMapping("/updateClassroomStatus.action")
	public ResultVO updateClassroomStatus(String classroomId, String status, HttpSession session) {
		ResultVO<TbCourseClassroom> resultVO = new ResultVO("更新成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			if (AssertUtils.isEmpty(classroomId) || AssertUtils.isEmpty(status) || (!"1".equals(status) && !"0".equals(status) )){
				resultVO.setCode("999");
				resultVO.setDescribe("非法参数");
			} else {
				TbCourseClassroom tbCourseClassroom = courseService.searchPOByPk(TbCourseClassroom.class, classroomId);
				if (AssertUtils.isEmpty(tbCourseClassroom)) {
					resultVO.setCode("1099");
					resultVO.setDescribe("更新失败，该课室不存在!");
				} else {
					TbCourseClassroom courseClassroom = new TbCourseClassroom();
					courseClassroom.setId(classroomId);
					courseClassroom.setUpdateTime(new Date());
					courseClassroom.setStatus(Integer.parseInt(status));
					courseService.updatePO(courseClassroom);
				}
			}
		}
		return resultVO;
	}

	@ResponseBody
	@RequestMapping("/getCourseInfo.action")
	public ResultVO getCourseInfo(String courseId, HttpSession session) {
		ResultVO<TbCourseInfo> resultVO = new ResultVO("查询成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			if (AssertUtils.isEmpty(courseId)) {
				resultVO.setCode("999");
				resultVO.setDescribe("非法参数");
			} else {
				TbCourseInfo courseInfo = courseService.searchPOByPk(TbCourseInfo.class, courseId);
				if (AssertUtils.isEmpty(courseInfo)) {
					resultVO.setCode("1099");
					resultVO.setDescribe("获取课程信息失败，该课程不存在！");
				} else {
					resultVO.setData(courseInfo);
				}
			}
		}
		return resultVO;
	}

	@ResponseBody
	@RequestMapping("/getCoursePage.action")
	public ResultVO getCoursePage(@RequestParam("inputSearch") String inputSearch, @RequestParam
			(value = "curPage", required = false) String curPage, HttpSession session) {
		ResultVO<Page<TbCourseInfo>> resultVO = new ResultVO("查询成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			Page<TbCourseInfo> page = new Page<TbCourseInfo>();
			if (curPage == null || curPage == "") {
				curPage = "1";
			}
			page.setCurPage(Integer.parseInt(curPage));
			page.setPageSize(10);

			Map<String, Object> searchMap = new HashMap<>();
			if (!AssertUtils.isEmpty(inputSearch)) {
				searchMap.put("inputSearch", "%" + inputSearch + "%");
			}
			courseService.getCoursePage(searchMap, page);
			resultVO.setData(page);
		}
		return resultVO;
	}

	/*@ResponseBody
	@RequestMapping("/addCourseInfo.action")
	public ResultVO addCourseInfo(TbCourseInfo tbCourseInfo, HttpSession session) {
		ResultVO<TbCourseClassroom> resultVO = new ResultVO("添加成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			if (AssertUtils.isEmpty(tbCourseClassroom)) {
				resultVO.setCode("999");
				resultVO.setDescribe("非法参数");
			} else {
				Map<String, Object> searchMap = new HashMap<>();
				searchMap.put("classroom_name", tbCourseClassroom.getClassroomName());
				TbCourseClassroom courseClassroom = courseService.getCourseClassroomInfo(searchMap);
				if (!AssertUtils.isEmpty(courseClassroom)) {
					resultVO.setCode("1099");
					resultVO.setDescribe("添加课室失败，该课室名已经存在");
				} else {
					tbCourseClassroom.setId(UUID.randomUUID().toString());
					tbCourseClassroom.setCreateTime(new Date());
					tbCourseClassroom.setCreator(adminUser.getUserName());
					tbCourseClassroom.setStatus(1);
					courseService.insertPO(tbCourseClassroom);
				}
			}
		}
		return resultVO;
	}*/

	@ResponseBody
	@RequestMapping("/delCourseById.action")
	public ResultVO delCourseById(String courseId, HttpSession session) {
		ResultVO<TbCourseInfo> resultVO = new ResultVO("删除成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			if (AssertUtils.isEmpty(courseId)) {
				resultVO.setCode("999");
				resultVO.setDescribe("非法参数");
			} else {
				TbCourseInfo courseInfo = courseService.searchPOByPk(TbCourseInfo.class, courseId);
				if (AssertUtils.isEmpty(courseInfo)) {
					resultVO.setCode("1099");
					resultVO.setDescribe("删除失败，课程不存在！");
				} else {
					courseService.delCourseById(courseId);
				}
			}
		}
		return resultVO;
	}
/*
	@ResponseBody
	@RequestMapping("/updateClassroomInfo.action")
	public ResultVO updateClassroomInfo(TbCourseClassroom tbCourseClassroom, HttpSession session) {
		ResultVO<TbCourseTerm> resultVO = new ResultVO("更新成功");
		TbAdminUserPO adminUser = (TbAdminUserPO) session.getAttribute("adminUser");
		if (AssertUtils.isEmpty(adminUser)) {
			resultVO.setCode("999");
			resultVO.setDescribe("请进行先登录！");
		} else {
			if (AssertUtils.isEmpty(tbCourseClassroom) || AssertUtils.isEmpty(tbCourseClassroom.getId())) {
				resultVO.setCode("999");
				resultVO.setDescribe("非法参数");
			} else {
				TbCourseClassroom courseClassroom = courseService.searchPOByPk(TbCourseClassroom.class, tbCourseClassroom.getId());
				if (AssertUtils.isEmpty(courseClassroom)) {
					resultVO.setCode("1099");
					resultVO.setDescribe("更新失败，该课室不存在!");
				} else {
					tbCourseClassroom.setUpdateTime(new Date());
					courseService.updatePO(tbCourseClassroom);
				}
			}
		}
		return resultVO;
	}*/
}
