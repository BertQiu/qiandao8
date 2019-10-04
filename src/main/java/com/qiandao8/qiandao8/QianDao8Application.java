package com.qiandao8.qiandao8;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiandao8.qiandao8.util.MD5Utils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;

@SpringBootApplication
@MapperScan("com.qiandao8.qiandao8.mapper")
public class QianDao8Application {

    public static void main(String[] args) {
        SpringApplication.run(QianDao8Application.class, args);

    }

}
