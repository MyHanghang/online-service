package online.mybatis.mapper;

import java.util.List;

import online.mybatis.model.Tbrecord;

/**
 * 学生个人操作记录mapper接口层
 */
public interface TbrecordMapper {

    int deleteByPrimaryKey(String id);

    int insert(Tbrecord record);

    /**
     * 通过学生个人操作记录controller层，用session来进行查询指定用户的操作记录
     *
     * @param id
     * @return
     */
    Tbrecord selectByPrimaryKey(String id);

    /**
     * @param
     * @return
     */
    List<Tbrecord> selectByPrimaryKey2(String rid);

    List<Tbrecord> selectOtherRecord();

    int updateByPrimaryKey(Tbrecord record);

    List<Tbrecord> selectByObj(Tbrecord record);

    List<Tbrecord> selectByObjLike(Tbrecord record);
}