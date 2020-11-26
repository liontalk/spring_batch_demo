package cn.liontalk.mysql2file.service;

import cn.liontalk.mysql2file.entity.Music;

import java.util.List;
import java.util.Map;

public interface MusicService {

    List<Music> queryInfoById(Map<String, Integer> map);

}
