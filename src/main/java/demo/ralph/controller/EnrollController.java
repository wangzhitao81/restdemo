package demo.ralph.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.ralph.common.MyConstants;
import demo.ralph.dto.CourseDto;
import demo.ralph.dto.EnrollDto;
import demo.ralph.dto.RestEntity;
import demo.ralph.dto.RestList;
import demo.ralph.entity.TCourse;
import demo.ralph.entity.TRelCourseStudentExample;
import demo.ralph.entity.TRelCourseStudentKey;
import demo.ralph.entity.TStudent;
import demo.ralph.mapper.TCourseMapper;
import demo.ralph.mapper.TRelCourseStudentMapper;
import demo.ralph.mapper.TStudentMapper;
import demo.ralph.mapper.ext.ExRelCourseStudentMapper;
@RestController
@RequestMapping("/enroll")
public class EnrollController {

	@Autowired
	private TStudentMapper tStudentMapper;
	@Autowired
	private TCourseMapper tCourseMapper;
	@Autowired
	private TRelCourseStudentMapper tRelCourseStudentMapper;
	@Autowired
	private ExRelCourseStudentMapper exRelCourseStudentMapper;
	
	@RequestMapping(value = "/{courseId}/{studentId}", method = RequestMethod.POST)
	public RestEntity<EnrollDto> enrollStudent(@PathVariable("courseId") Long courseId,@PathVariable("studentId") Long studentId){
		RestEntity<EnrollDto> result = new RestEntity<EnrollDto>();
		result.setData(new EnrollDto());
		TStudent tStudent = tStudentMapper.selectByPrimaryKey(studentId);
		if(tStudent == null){
			result.setResult(MyConstants.FAIL);
			result.setMsg("invalid student id");
		}
		TCourse tCourse = tCourseMapper.selectByPrimaryKey(courseId);
		if(tCourse == null){
			result.setResult(MyConstants.FAIL);
			result.setMsg("invalid course id");
		}
		TRelCourseStudentExample example = new TRelCourseStudentExample();
		example.createCriteria().andCourseIdEqualTo(courseId).andStudentIdEqualTo(studentId);
		List<TRelCourseStudentKey> relList = tRelCourseStudentMapper.selectByExample(example);
		if(relList != null && relList.size()>0){
			result.setResult(MyConstants.FAIL);
			result.setMsg("enrolled");
			result.getData().setCourseId(relList.get(0).getCourseId());
			result.getData().setStudentId(relList.get(0).getStudentId());
		}else{
			TRelCourseStudentKey tRel = new TRelCourseStudentKey();
			tRel.setCourseId(tCourse.getId());
			tRel.setStudentId(tStudent.getId());
			tRelCourseStudentMapper.insert(tRel);
			result.getData().setCourseId(courseId);
			result.getData().setStudentId(studentId);
		}
		return result;
	}
	@RequestMapping(value = "/{studentId}/courses", method = RequestMethod.GET)
	public RestList<CourseDto> enrollStudent(@PathVariable("studentId") Long studentId){
		RestList<CourseDto> restResult = new RestList<CourseDto>();
		restResult.setData(new ArrayList<CourseDto>());
		TStudent tStudent = tStudentMapper.selectByPrimaryKey(studentId);
		if(tStudent == null){
			restResult.setResult(MyConstants.FAIL);
			restResult.setMsg("invalid student id");
		}
		List<TCourse> list = exRelCourseStudentMapper.selectStudentEnrolled(studentId);
		for(TCourse c:list){
			CourseDto dto = new CourseDto();
			dto.setCode(c.getCode());
			dto.setId(c.getId());
			dto.setName(c.getName());
			restResult.getData().add(dto);
		}
		return restResult;
	}
}
