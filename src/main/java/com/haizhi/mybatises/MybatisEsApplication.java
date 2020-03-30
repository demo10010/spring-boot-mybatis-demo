package com.haizhi.mybatises;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.haizhi.mybatises.dao")
@EnableSwagger2
@SpringBootApplication
public class MybatisEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisEsApplication.class, args);
    }

}
