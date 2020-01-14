package com.aidou.api;


import com.aidou.api.common.cache.RedisDao;
import com.aidou.api.common.cache.RedisKey;
import com.aidou.api.service.*;
import com.aidou.api.vo.PageExtAction;
import com.aidou.api.vo.UserToken;
import com.aidou.api.vo.request.*;
import com.aidou.api.vo.respone.TopicItemRespone;
import com.aidou.dao.entity.*;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.enums.NoticeEnum;
import com.aidou.util.tool.*;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FromIdTest {

    @Resource
    private TopicService topicService;


    @Test
    public void test1() throws Exception {
        ArticleCommentSearchRequest request=new ArticleCommentSearchRequest();
        request.setPage(1);
        request.setLimit(5);
        request.setArticleId(1210095698546249728L);
        request.setType(2);
        AidouResult aidouResult = topicService.commentList(request);
        System.out.println(GsonUtil.gsonString(aidouResult));
    }

    @Test
    public void test2() throws Exception {

        TopicLikeRequest request=new TopicLikeRequest();
        request.setLikeStatus(1);
        request.setTopicId(141L);
        topicService.commentZan(request);

    }


    @Test
    public void common() throws Exception {
        CommentRRequest reques=new CommentRRequest();
        reques.setParentid(0L);
        reques.setContent("你好啊");
        reques.setTopicId(1210095698546249728L);
        topicService.commentTopic(reques);

    }

    @Before
    public void init() {
        CurrentUser user = new CurrentUser();
        user.setId(100088L);
        CurrentUser.set(user);
    }


}
