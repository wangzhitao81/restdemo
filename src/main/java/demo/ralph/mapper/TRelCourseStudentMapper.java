package demo.ralph.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import demo.ralph.entity.TRelCourseStudentExample;
import demo.ralph.entity.TRelCourseStudentKey;

public interface TRelCourseStudentMapper {
    int countByExample(TRelCourseStudentExample example);

    int deleteByExample(TRelCourseStudentExample example);

    @Delete({
        "delete from t_rel_course_student",
        "where student_id = #{studentId,jdbcType=BIGINT}",
          "and course_id = #{courseId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(TRelCourseStudentKey key);

    @Insert({
        "insert into t_rel_course_student (student_id, course_id)",
        "values (#{studentId,jdbcType=BIGINT}, #{courseId,jdbcType=BIGINT})"
    })
    int insert(TRelCourseStudentKey record);

    int insertSelective(TRelCourseStudentKey record);

    List<TRelCourseStudentKey> selectByExample(TRelCourseStudentExample example);

    int updateByExampleSelective(@Param("record") TRelCourseStudentKey record, @Param("example") TRelCourseStudentExample example);

    int updateByExample(@Param("record") TRelCourseStudentKey record, @Param("example") TRelCourseStudentExample example);
}