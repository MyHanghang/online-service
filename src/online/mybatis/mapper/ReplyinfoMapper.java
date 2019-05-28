package online.mybatis.mapper;

import java.util.List;

import online.mybatis.model.Replyinfo;

public interface ReplyinfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(Replyinfo record);


    Replyinfo selectByPrimaryKey(String id);


    int updateByPrimaryKey(Replyinfo record);
    
    List<Replyinfo> selectByObj(Replyinfo record);
    
    List<Replyinfo> selectByObjLike(Replyinfo record);
    
    List<Replyinfo> getList(Replyinfo record);
    
}