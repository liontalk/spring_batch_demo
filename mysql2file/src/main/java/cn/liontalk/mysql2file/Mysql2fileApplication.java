package cn.liontalk.mysql2file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;

@SpringBootApplication
//@EnableBatchProcessing
@MapperScan("cn.liontalk.mysql2file.dao")
public class Mysql2fileApplication {

    public static void main(String[] args) {
        SpringApplication.run(Mysql2fileApplication.class, args);
    }

}
