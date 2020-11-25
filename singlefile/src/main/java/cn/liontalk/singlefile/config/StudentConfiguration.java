package cn.liontalk.singlefile.config;

import cn.liontalk.singlefile.entity.People;
import cn.liontalk.singlefile.entity.Student;
import cn.liontalk.singlefile.processer.StudentProcessor;
import cn.liontalk.singlefile.reader.CommonFileItemReader;
import cn.liontalk.singlefile.writer.CommonFileItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class StudentConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private StudentProcessor studentProcessor;

    @Bean
    public Job studentJob() {
        return jobBuilderFactory.get("studentJob")
                .start(studentStep())
                .build();
    }

    @Bean
    public Step studentStep() {
        return stepBuilderFactory.get("studentStep")
                .<People, Student>chunk(10)
                .reader(peopleCommonFileItemReader())
                .processor(studentProcessor)
                .writer(studentCommonFileItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public CommonFileItemReader<People> peopleCommonFileItemReader() {
        return new CommonFileItemReader<>(People.class);
    }


    public CommonFileItemWriter<Student> studentCommonFileItemWriter() {
        return new CommonFileItemWriter<>(Student.class);
    }
}
