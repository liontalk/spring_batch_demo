package cn.liontalk.helloworld;

import cn.liontalk.helloworld.config.BatchConfig;
import cn.liontalk.helloworld.config.HelloWorldJobConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.batch.operations.NoSuchJobException;


@RunWith(SpringRunner.class)
@SpringBootTest
class HelloworldApplicationTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void testHelloWorldJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        System.out.println("================================");
        System.out.println(jobExecution.getExitStatus().getExitCode());
    }

    @Configuration
    @Import({BatchConfig.class, HelloWorldJobConfig.class})
    static class BatchTestConfig {

        @Autowired
        private Job helloWorldJob;

        @Bean
        JobLauncherTestUtils jobLauncherTestUtils()
                throws NoSuchJobException {
            JobLauncherTestUtils jobLauncherTestUtils =
                    new JobLauncherTestUtils();
            jobLauncherTestUtils.setJob(helloWorldJob);

            return jobLauncherTestUtils;
        }
    }
}
