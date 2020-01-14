package com.aidou.api;

import com.aidou.util.tool.IdWorker;
import com.aidou.util.tool.ImageUtil;
import com.aidou.util.tool.JwtConfig;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by 37269 on 2018/6/2.
 */

@EnableCaching
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.aidou.*")
@MapperScan(basePackages = "com.aidou.dao.mapper")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }


    @Bean
    public IdWorker idWorker() {
        IdWorker idWorker = new IdWorker();
        return idWorker;
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }

    @Bean
    public ImageUtil imageUtil() {
        return new ImageUtil();
    }


    @Bean
    public WxMpInMemoryConfigStorage wechatBean() {
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId("wx938259363d663a46");
        wxMpInMemoryConfigStorage.setSecret("953e65efd2edbbf948f8e47161490545");
        return  wxMpInMemoryConfigStorage;
    }
    @Bean
    public WxPayServiceImpl  wxPayServiceImpl(){
        WxPayServiceImpl wxPayService=new WxPayServiceImpl();
        WxPayConfig  wxPayConfig=new WxPayConfig();
        wxPayConfig.setAppId("wx938259363d663a46");
        //商户号
        wxPayConfig.setMchId("1556840741");
        //微信支付平台商户API密钥
        wxPayConfig.setMchKey("6gbyqEawwEQ1iLzYMvraNzRb1Q8ac5Ym");
        wxPayService.setConfig(wxPayConfig);
        return wxPayService;
    }

}
