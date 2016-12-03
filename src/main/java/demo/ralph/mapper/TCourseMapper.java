package demo.ralph.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import demo.ralph.entity.TCourse;
import demo.ralph.entity.TCourseExample;

public interface TCourseMapper {
    int countByExample(TCourseExample example);

    int deleteByExample(TCourseExample example);

    @Delete({
        "delete from t_course",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_course (id, code, name)",
        "values (#{id,jdbcType=BIGINT}, #{code,jdbcType=CHAR}, #{name,jdbcType=VARCHAR})"
    })
    int insert(TCourse record);

    int insertSelective(TCourse record);

    List<TCourse> selectByExample(TCourseExample example);

    @Select({
        "select",
        "id, code, name",
        "from t_course",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    TCourse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TCourse record, @Param("example") TCourseExample example);

    int updateByExample(@Param("record") TCourse record, @Param("example") TCourseExample example);

    int updateByPrimaryKeySelective(TCourse record);

    @Update({
        "update t_course",
        "set code = #{code,jdbcType=CHAR},",
          "name = #{name,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TCourse record);
}