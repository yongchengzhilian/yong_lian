package com.aidou.api;

import com.aidou.api.enums.LineSourceType;
import com.aidou.api.service.*;
import com.aidou.api.vo.respone.*;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.tool.GsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchmakerTest {

    @Autowired
   private UserCheckInfoService  userCheckInfoService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private LineService lineService;

    @Autowired
    private FriendApplicationService friendApplicationService;


    @Test
    public  void findMatchmakerInfo(){
        Integer integer = lineService.addLine(100721L, LineSourceType.GIFT, 1);
        System.out.println(integer);
    }






    @Before
    public   void initUser(){
        CurrentUser currentUser=new CurrentUser();
        currentUser.setId(1154721172981669888L);
        currentUser.setStatus(2);
        currentUser.setRealName(true);
        currentUser.setNikeName("张三");
        CurrentUser.set(currentUser);
    }




}
