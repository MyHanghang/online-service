package online.mybatis.mapper;

import java.util.List;

import online.mybatis.model.Messageinfo;

public interface MessageinfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(Messageinfo record);


    Messageinfo selectByPrimaryKey(String id);


    int updateByPrimaryKey(Messageinfo record);

    List<Messageinfo> selectByObj(Messageinfo record);

    List<Messageinfo> selectByObjLike(Messageinfo record);

    /**
     * 学生端，点击创新训练后，传入创新训练的类型 type
     * 到数据查找出所有与此类型相对于的所有对象并封装成List集合返回到前端
     *
     * @param record
     * @return
     */
    List<Messageinfo> getQuery(Messageinfo record);
}