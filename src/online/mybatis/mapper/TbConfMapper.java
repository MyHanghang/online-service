package online.mybatis.mapper;

import java.util.List;

import online.mybatis.model.TbConf;

public interface TbConfMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbConf record);


    TbConf selectByPrimaryKey(String id);


    int updateByPrimaryKey(TbConf record);
    
    List<TbConf> selectByObj(TbConf record);
    
    List<TbConf> selectByObjLike(TbConf record);
    
    int deleteByTemp();
}