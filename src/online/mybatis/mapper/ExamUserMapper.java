package online.mybatis.mapper;

import java.util.List;

import online.mybatis.model.ExamUser;
import online.mybatis.model.ExamUserExample;
import online.mybatis.model.ExamUserExcel;
import org.apache.ibatis.annotations.Param;

public interface ExamUserMapper {
    int countByExample(ExamUserExample example);

    int deleteByExample(ExamUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExamUser record);

    int insertSelective(ExamUser record);

    int insertExcel(ExamUserExcel user);

    /**
     * 通过我的关注的多个学生ID来查询TA的个人信息
     *
     * @param example
     * @return
     */
//    List<ExamUser> selectByMyCare(String id);

    List<ExamUser> selectByExample(ExamUserExample example);

    ExamUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExamUser record, @Param("example") ExamUserExample example);

    int updateByExample(@Param("record") ExamUser record, @Param("example") ExamUserExample example);

    int updateByPrimaryKeySelective(ExamUser record);

    int updateByPrimaryKey(ExamUser record);
}