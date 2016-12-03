package demo.ralph.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.ralph.dto.CourseDto;
import demo.ralph.entity.TCourse;
import demo.ralph.entity.TCourseExample;
import demo.ralph.entity.TCourseExample.Criteria;
import demo.ralph.mapper.TCourseMapper;
import demo.ralph.mapper.ext.ExCourseMapper;

@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private TCourseMapper tCourseMapper;
	@Autowired
	private ExCourseMapper exCourseMapper;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public CourseDto save(@RequestBody CourseDto courseDto){
		TCourse tCourse = new TCourse();
		tCourse.setCode(courseDto.getCode());
		tCourse.setName(courseDto.getName());
		exCourseMapper.insertAndGetId(tCourse);
		courseDto.setId(tCourse.getId());
		return courseDto;
	}
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public CourseDto delete(@PathVariable("id") Integer id){
		TCourse tCourse = tCourseMapper.selectByPrimaryKey(id.longValue());
		CourseDto dto = new CourseDto();
		if(tCourse != null){
			tCourseMapper.deleteByPrimaryKey(id.longValue());
			dto.setCode(tCourse.getCode());
			dto.setName(tCourse.getName());
			dto.setId(tCourse.getId().longValue());
		}
		return dto;
	}
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public CourseDto update(@RequestBody CourseDto courseDto){
		TCourse tCourse = tCourseMapper.selectByPrimaryKey(courseDto.getId());
		if(tCourse != null){
			tCourse.setCode(courseDto.getCode());
			tCourse.setName(courseDto.getName());
			tCourseMapper.updateByPrimaryKey(tCourse);
			courseDto.setCode(tCourse.getCode());
			courseDto.setName(tCourse.getName());
		}
		return courseDto;
	}
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public CourseDto get(@PathVariable("id") Long id){
		TCourse tCourse = tCourseMapper.selectByPrimaryKey(id);
		CourseDto dto = new CourseDto();
		if(tCourse != null){
			dto.setId(tCourse.getId());
			dto.setCode(tCourse.getCode());
			dto.setName(tCourse.getName());
			tCourseMapper.updateByPrimaryKey(tCourse);
		}
		return dto;
	}
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public List<CourseDto> search(@RequestBody CourseDto courseDto){
		TCourseExample example = new TCourseExample();
		Criteria criteria = example.createCriteria();
		if(courseDto.getCode() != null && courseDto.getCode().trim()!=""){
			criteria.andCodeLike("%"+courseDto.getCode()+"%");
		}
		if(courseDto.getName() != null && courseDto.getName().trim()!=""){
			criteria.andNameLike("%"+courseDto.getName()+"%");
		}
		List<TCourse> list = tCourseMapper.selectByExample(example);
		List<CourseDto> dtoList = new ArrayList<CourseDto>();
		for(TCourse t:list){
			CourseDto dto = new CourseDto();
			dto.setCode(t.getCode());
			dto.setName(t.getName());
			dto.setId(t.getId());
			dtoList.add(dto);
		}
		return dtoList;
	}
	
}
