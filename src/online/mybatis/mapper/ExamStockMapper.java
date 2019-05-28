package online.mybatis.mapper;

import java.util.List;
import online.mybatis.model.ExamStock;
import online.mybatis.model.ExamStockExample;
import online.mybatis.model.ExamStockExcel;
import online.mybatis.model.ExamUserExcel;
import org.apache.ibatis.annotations.Param;

public interface ExamStockMapper {
    int countByExample(ExamStockExample example);

    int deleteByExample(ExamStockExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExamStock record);

    int insertExcel(ExamStockExcel user);

    int insertSelective(ExamStock record);

    List<ExamStock> selectByExample(ExamStockExample example);

    ExamStock selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExamStock record, @Param("example") ExamStockExample example);

    int updateByExample(@Param("record") ExamStock record, @Param("example") ExamStockExample example);

    int updateByPrimaryKeySelective(ExamStock record);

    int updateByPrimaryKey(ExamStock record);
}