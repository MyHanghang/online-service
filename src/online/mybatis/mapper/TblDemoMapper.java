package online.mybatis.mapper;

import java.util.List;

import online.mybatis.dao.TblDemoExample;
import online.mybatis.model.TblDemo;

import org.apache.ibatis.annotations.Param;

public interface TblDemoMapper {
    int countByExample(TblDemoExample example);

    int deleteByExample(TblDemoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TblDemo record);

    int insertSelective(TblDemo record);

    List<TblDemo> selectByExample(TblDemoExample example);

    TblDemo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TblDemo record, @Param("example") TblDemoExample example);

    int updateByExample(@Param("record") TblDemo record, @Param("example") TblDemoExample example);

    int updateByPrimaryKeySelective(TblDemo record);

    int updateByPrimaryKey(TblDemo record);
}