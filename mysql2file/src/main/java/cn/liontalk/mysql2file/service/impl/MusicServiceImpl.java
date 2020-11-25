package cn.liontalk.mysql2file.service.impl;

import cn.liontalk.mysql2file.dao.MusicDao;
import cn.liontalk.mysql2file.entity.Music;
import cn.liontalk.mysql2file.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicDao musicDao;

    @Override
    public List<Music> queryInfoById(Map<String, Integer> map) {
        List<Music> list =  musicDao.queryInfoById(map);
        return list;
    }
}
