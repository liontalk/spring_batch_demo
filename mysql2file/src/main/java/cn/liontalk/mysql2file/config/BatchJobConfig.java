package cn.liontalk.mysql2file.config;


import cn.liontalk.mysql2file.common.CommonItemReader;
import cn.liontalk.mysql2file.entity.Music;
import cn.liontalk.mysql2file.service.MusicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class BatchJobConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    private MusicService musicService;


    private static final String JOB = "job";


    private static final String STEP = "step";


    //配置一个Job

    @Bean(name = JOB)
    public Job job() {
        return jobBuilderFactory.get(JOB)
                .start(step())
                .build();
    }

    //配置一个Step
    @Bean(name = STEP)
    public Step step() {
        return stepBuilderFactory.get(STEP)
                .<Music, Music>chunk(2)
                .reader(itemReader())
                .writer(itemWriter())
                .build();

    }

    //配置itemReader
    @Bean("itemReader")
    @StepScope
    public CommonItemReader<Music> itemReader() {
        System.out.println("开始查询数据库");
        Map<String, Integer> map = new HashMap<>();
        map.put("id", 2);
        List<Music> musicList = musicService.queryInfoById(map);
        return new CommonItemReader<Music>(musicList);
    }


    //配置itemWriter

//    @Bean("itemWriter")
//    @StepScope
//    public FlatFileItemWriter<Music> itemWriter() {
//        System.out.println("开始写入文件中");
//        FlatFileItemWriter<Music> writer = new FlatFileItemWriter<>();
//        writer.setResource(new FileSystemResource("E:\\spring_batch_test_file\\music.txt"));//系统目录
//        //将Music对象转换成字符串,并输出到文件
//        writer.setLineAggregator(new LineAggregator<Music>() {
//            @Override
//            public String aggregate(Music music) {
//                ObjectMapper mapper = new ObjectMapper();
//                String str = null;
//                try {
//                    str = mapper.writeValueAsString(music);
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//                return str;
//            }
//        });
//        return writer;
//    }


    @Bean("itemWriter")
    @StepScope
    public ItemWriter<? super Music> itemWriter() {
        return new ItemWriter<Music>() {
            @Override
            public void write(List<? extends Music> items) throws Exception {
                for (Music item : items) {
                    System.out.println("output writer data: " + item);
                }
            }
        };
    }
}
