package cn.liontalk.mysql2file.controller;


import cn.liontalk.mysql2file.config.BatchJobConfig;
import cn.liontalk.mysql2file.entity.Music;
import cn.liontalk.mysql2file.service.MusicService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
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

    @Autowired
    private BatchJobConfig batchJobConfig;

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
