package cn.liontalk.mysql2file.config;


import cn.liontalk.mysql2file.common.CommonItemReader;
import cn.liontalk.mysql2file.entity.Music;
import cn.liontalk.mysql2file.service.MusicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class BatchJobConfig implements StepExecutionListener {

    //创建任务对象
    @Autowired
    JobBuilderFactory jobBuilderFactory;

    //由任务决定step执行
    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    private MusicService musicService;


    @Autowired
    private DataSource dataSource;


    private static final String JOB = "job";


    private static final String STEP = "step";

    private Map<String,JobParameter> parameters;


    //创建一个任务
    //配置一个Job
    @Bean(name = JOB)
    public Job job(  ) {
        return jobBuilderFactory
                .get(JOB)
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    //配置一个Step
    @Bean(name = STEP)
    public Step step() {
        return stepBuilderFactory.get(STEP)
                .listener(this)
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println(parameters.get("info"));
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
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


    //配置itemReader
    @Bean("itemReader")
    @StepScope
    public JdbcPagingItemReader<Music> jdbcPagingItemReader() {
        System.out.println("开始查询数据库");
        Map<String, Integer> map = new HashMap<>();
        map.put("id", 2);
       // List<Music> musicList = musicService.queryInfoById(map);
        JdbcPagingItemReader<Music> reader = new JdbcPagingItemReader<Music>();
        reader.setDataSource(dataSource);
        reader.setFetchSize(2);
        reader.setPageSize(1);
        reader.setRowMapper(new RowMapper<Music>() {
            @Override
            public Music mapRow(ResultSet resultSet, int i) throws SQLException {
                return null;
            }
        });
        return reader;
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

    @Override
    public void beforeStep(StepExecution stepExecution) {
        parameters = stepExecution.getJobParameters().getParameters();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
