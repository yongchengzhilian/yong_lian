package com.aidou.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by 37269 on 2018/6/2.
 */
@SpringBootApplication
public class DaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaoApplication.class,args);
    }
}
