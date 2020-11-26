package cn.liontalk.mysql2file.dao;

import cn.liontalk.mysql2file.entity.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MusicDao {

    //通过id查询数据库记录
    List<Music> queryInfoById(@Param("param") Map<String, Integer> map);
}