package online.mybatis.mapper;

import java.util.List;
import online.mybatis.model.ExamVideo;
import online.mybatis.model.ExamVideoExample;
import org.apache.ibatis.annotations.Param;

public interface ExamVideoMapper {
    int countByExample(ExamVideoExample example);

    int deleteByExample(ExamVideoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExamVideo record);

    int insertSelective(ExamVideo record);

    List<ExamVideo> selectByExample(ExamVideoExample example);

    ExamVideo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExamVideo record, @Param("example") ExamVideoExample example);

    int updateByExample(@Param("record") ExamVideo record, @Param("example") ExamVideoExample example);

    int updateByPrimaryKeySelective(ExamVideo record);

    int updateByPrimaryKey(ExamVideo record);
}