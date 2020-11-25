package cn.liontalk.file2oracle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.liontalk.file2oracle.dao")
public class File2oracleApplication {

    public static void main(String[] args) {
        SpringApplication.run(File2oracleApplication.class, args);
    }

}
