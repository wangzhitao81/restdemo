package demo.ralph.mapper.ext;

import java.util.List;

import demo.ralph.entity.TCourse;

public interface ExRelCourseStudentMapper {
	public List<TCourse> selectStudentEnrolled(Long studentId);
}
