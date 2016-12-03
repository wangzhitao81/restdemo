package demo.ralph.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import demo.ralph.entity.TStudent;
import demo.ralph.entity.TStudentExample;

public interface TStudentMapper {
    int countByExample(TStudentExample example);

    int deleteByExample(TStudentExample example);

    @Delete({
        "delete from t_student",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_student (id, id_number, ",
        "name, gender)",
        "values (#{id,jdbcType=BIGINT}, #{idNumber,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{gender,jdbcType=TINYINT})"
    })
    int insert(TStudent record);

    int insertSelective(TStudent record);

    List<TStudent> selectByExample(TStudentExample example);

    @Select({
        "select",
        "id, id_number, name, gender",
        "from t_student",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    TStudent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TStudent record, @Param("example") TStudentExample example);

    int updateByExample(@Param("record") TStudent record, @Param("example") TStudentExample example);

    int updateByPrimaryKeySelective(TStudent record);

    @Update({
        "update t_student",
        "set id_number = #{idNumber,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "gender = #{gender,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TStudent record);
}