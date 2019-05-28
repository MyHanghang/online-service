package online.mybatis.mapper;

import java.util.List;
import online.mybatis.model.ExamPaperDetail;
import online.mybatis.model.ExamPaperDetailExample;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 23:31
 * @Version V2.0
 */
public interface ExamPaperDetailMapper {
    int countByExample(ExamPaperDetailExample example);

    int deleteByExample(ExamPaperDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExamPaperDetail record);

    int insertSelective(ExamPaperDetail record);

    List<ExamPaperDetail> selectByExample(ExamPaperDetailExample example);

    ExamPaperDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExamPaperDetail record, @Param("example") ExamPaperDetailExample example);

    int updateByExample(@Param("record") ExamPaperDetail record, @Param("example") ExamPaperDetailExample example);

    int updateByPrimaryKeySelective(ExamPaperDetail record);

    int updateByPrimaryKey(ExamPaperDetail record);
}