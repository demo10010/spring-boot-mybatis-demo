package com.haizhi.mybatises;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Driver;

@MapperScan("com.haizhi.mybatises.dao")
@SpringBootApplication
public class MybatisEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisEsApplication.class, args);
    }

}
