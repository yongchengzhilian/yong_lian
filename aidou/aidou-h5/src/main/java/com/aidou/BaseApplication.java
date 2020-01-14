package com.aidou;

import com.aidou.util.tool.IdWorker;
import com.aidou.util.tool.ImageUtil;
import com.aidou.util.tool.JwtConfig;
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
@EnableTransactionManagement
@ComponentScan("com.aidou.*")
@MapperScan(basePackages = "com.aidou.dao.mapper")
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class,args);
    }


    @Bean
    public IdWorker idWorker(){
        IdWorker  idWorker=new IdWorker();
        return idWorker;
    }

    @Bean
    public JwtConfig jwtConfig(){
        return new JwtConfig();

    }

    @Bean
    public ImageUtil imageUtil(){
        return new ImageUtil();
    }






}
