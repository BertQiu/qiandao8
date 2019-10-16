package com.qiandao8.qiandao8;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qiandao8.qiandao8.mapper")
public class QianDao8Application {
    public static void main(String[] args) {
        SpringApplication.run(QianDao8Application.class, args);
    }
}
