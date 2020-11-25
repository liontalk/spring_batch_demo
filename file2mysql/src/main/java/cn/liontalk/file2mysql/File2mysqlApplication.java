package cn.liontalk.file2mysql;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class File2mysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(File2mysqlApplication.class, args);
    }

}
