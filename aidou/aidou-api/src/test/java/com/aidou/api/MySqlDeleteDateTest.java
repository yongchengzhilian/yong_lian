package com.aidou.api;

import com.aidou.api.service.FriendRecommendService;
import com.aidou.api.vo.user.ReommendUserVo;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.tool.GsonUtil;
import com.aidou.util.tool.JwtConfig;
import com.aidou.util.tool.JwtUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/26 14:17
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySqlDeleteDateTest {

    @Autowired
    private JwtConfig jwtConfig;

    @Test
    public void test1(){
        String token = JwtUtils.createToken(jwtConfig, 1166611174883704832l);
        System.out.println(token);
    }

    @Before
    public   void initUser(){
        CurrentUser currentUser=new CurrentUser();
        currentUser.setId(1154646764657168384L);
        currentUser.setStatus(1);
        currentUser.setRealName(false);
        currentUser.setNikeName("张三");
        CurrentUser.set(currentUser);
    }


}
