package cn.liontalk.mysql2file.controller;


import cn.liontalk.mysql2file.entity.Music;
import cn.liontalk.mysql2file.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MusicController {


    @Autowired
    MusicService musicService;


    @GetMapping(value = "/test")
    public List<Music> getMusicList(){
        Map<String,Integer> map = new HashMap<>();
        map.put("id",2);
        return  musicService.queryInfoById(map);
    }


}
