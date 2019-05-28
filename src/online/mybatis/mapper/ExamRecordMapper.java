package online.mybatis.mapper;

import java.util.List;

import online.mybatis.model.ExamRecord;
import online.mybatis.model.ExamRecordExample;
import org.apache.ibatis.annotations.Param;

public interface ExamRecordMapper {
    int countByExample(ExamRecordExample example);

    int deleteByExample(ExamRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExamRecord record);

    int insertSelective(ExamRecord record);


    List<ExamRecord> selectByExample(ExamRecordExample example);

    ExamRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExamRecord record, @Param("example") ExamRecordExample example);

    int updateByExample(@Param("record") ExamRecord record, @Param("example") ExamRecordExample example);

    int updateByPrimaryKeySelective(ExamRecord record);

    /**
     * 提交评语
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(ExamRecord record);

    int updateByPrimaryKeyAuto(ExamRecord record);
}