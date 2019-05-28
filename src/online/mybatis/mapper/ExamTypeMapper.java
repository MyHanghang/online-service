package online.mybatis.mapper;

import java.util.List;
import online.mybatis.model.ExamType;
import online.mybatis.model.ExamTypeExample;
import org.apache.ibatis.annotations.Param;

public interface ExamTypeMapper {
    int countByExample(ExamTypeExample example);

    int deleteByExample(ExamTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExamType record);

    int insertSelective(ExamType record);

    List<ExamType> selectByExample(ExamTypeExample example);

    ExamType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExamType record, @Param("example") ExamTypeExample example);

    int updateByExample(@Param("record") ExamType record, @Param("example") ExamTypeExample example);

    int updateByPrimaryKeySelective(ExamType record);

    int updateByPrimaryKey(ExamType record);
}