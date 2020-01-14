package com.aidou.api;

import com.aidou.api.service.*;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.exception.ExpiredTokenException;
import com.aidou.util.exception.InvalidTokenException;
import com.aidou.util.tool.JwtConfig;
import com.aidou.util.tool.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/18 10:06
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolTest {

    @Autowired
    private JwtConfig jwtConfig;

    @Test
    public void test2() throws ExpiredTokenException, InvalidTokenException {

//        Jws<Claims> jwsClaims = JwtUtils.parseClaims(jwtConfig, "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMDAiLCJpc3MiOiJodHRwczovL2FpZG91LmNvbSIsImlhdCI6MTU3ODY0NDc3MywiZXhwIjoxNTgxMjM2NzczfQ.e-n4ekhjZbk61jvV09vE3BpKnz2ifTsdJiAKRkjBaZKUcpNB-TbgC2ij7IzFB0WifMypitgc51kSJUH3-_6MpA");
//        final String strUserId = jwsClaims.getBody().getSubject();
//        System.out.println(strUserId);
        String token = JwtUtils.createToken(jwtConfig, 100088L);
        System.out.println(token);
    }

    @Before
    public void user() {
        CurrentUser currentUser = new CurrentUser();
        currentUser.setSex(1);
        currentUser.setNikeName("张三");
        currentUser.setStatus(3);
        currentUser.setId(100088L);
        CurrentUser.set(currentUser);
    }


}
