package online.mybatis.mapper;

import java.util.List;
import online.mybatis.model.ExamRecordDetail;
import online.mybatis.model.ExamRecordDetailExample;
import org.apache.ibatis.annotations.Param;

public interface ExamRecordDetailMapper {
    int countByExample(ExamRecordDetailExample example);

    int deleteByExample(ExamRecordDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExamRecordDetail record);

    int insertSelective(ExamRecordDetail record);

    List<ExamRecordDetail> selectByExample(ExamRecordDetailExample example);

    ExamRecordDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExamRecordDetail record, @Param("example") ExamRecordDetailExample example);

    int updateByExample(@Param("record") ExamRecordDetail record, @Param("example") ExamRecordDetailExample example);

    int updateByPrimaryKeySelective(ExamRecordDetail record);

    int updateByPrimaryKey(ExamRecordDetail record);

    String selectAnswerByRecordid(String recordid);

    String selectMyAnswerByRecordid(String recordid);
}