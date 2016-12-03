package demo.ralph.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.ralph.dto.StudentDto;
import demo.ralph.entity.TStudent;
import demo.ralph.mapper.TStudentMapper;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private TStudentMapper tStudentMapper;
	
	@RequestMapping(value = "/{id}")
	public StudentDto select(@PathVariable Long id){
		StudentDto model = new StudentDto();
		TStudent entity = tStudentMapper.selectByPrimaryKey(id);
		if(entity != null){
			model.setGender(entity.getGender());
			model.setId(entity.getId());
			model.setName(entity.getName());
		}else{
			model.setName("ralph");
		}
		return model;
	}

}
