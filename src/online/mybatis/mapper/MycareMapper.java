package online.mybatis.mapper;

import java.util.List;

import online.mybatis.model.Mycare;

public interface MycareMapper {
    int deleteByPrimaryKey(String id);

    int deleteByObj(Mycare record);

    int insert(Mycare record);


    Mycare selectByPrimaryKey(String id);


    int updateByPrimaryKey(Mycare record);

    /**
     * 查询我关注的同学
     *
     * @param record 传参包括该学生的id和关注的类型
     * @return
     */
    List<Mycare> selectByObj(Mycare record);

    List<Mycare> selectByObjLike(Mycare record);
}