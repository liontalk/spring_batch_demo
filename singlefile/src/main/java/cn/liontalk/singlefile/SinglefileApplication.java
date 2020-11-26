package cn.liontalk.singlefile;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SinglefileApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext run =   SpringApplication.run(SinglefileApplication.class, args);
        run(run);
    }

    private static void run(ApplicationContext ctx) throws Exception{
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
        JobParameters jobParameters = new JobParametersBuilder().addDate("test", new Date()).toJobParameters();
        JobExecution studentJob = jobLauncher.run(ctx.getBean("studentJob", Job.class), jobParameters);
    }

}
