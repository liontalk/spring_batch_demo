package cn.liontalk.mysql2file.controller;


import cn.liontalk.mysql2file.entity.Music;
import cn.liontalk.mysql2file.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
//        JobLauncher jobLauncher = new JobLauncher() {
//            @Override
//            public JobExecution run(Job job, JobParameters jobParameters) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
//                JobExecution jobExecution = new JobExecution();
//
//                return jobExecution;
//            }
//        };
        return  musicService.queryInfoById(map);
    }


}
