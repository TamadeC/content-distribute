package com.processing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chen
 * @createDate 2024/6/4 22:23
 */

@SpringBootApplication
@MapperScan("com.processing.mapper")
public class DataProcessingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataProcessingApplication.class, args);
    }
}
