package com.aidou.api;

import com.aidou.api.service.FriendRecommendService;
import com.aidou.api.service.TopicService;
import com.aidou.api.service.WeChatMessagePushService;
import com.aidou.api.vo.PageExtAction;
import com.aidou.api.vo.request.ArticleCommentSearchRequest;
import com.aidou.api.vo.request.TopicListRequest;
import com.aidou.api.vo.request.TopicVoteRequest;
import com.aidou.api.vo.request.UserRecommendListRequest;
import com.aidou.api.vo.respone.TopicItemRespone;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.entity.wechat.WeChatMessageModel;
import com.aidou.util.enums.UserStatusEnum;
import com.aidou.util.tool.GsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/31 14:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAddTest {

    @Resource
    private TopicService topicService;

    @Test
    public void test3(){
        PageExtAction page=new PageExtAction();
        page.setLimit(5);
        page.setPage(1);
        AidouResult aidouResult = topicService.topicMessage(page);
        System.out.println(GsonUtil.gsonString(aidouResult));
    }


    @Test
    public  void test1(){
        ArticleCommentSearchRequest request=new ArticleCommentSearchRequest();
        request.setArticleId(1210038274053357568L);
        request.setType(1);
        request.setLimit(5);
        request.setPage(1);
        AidouResult aidouResult = topicService.commentList(request);
        System.out.println(GsonUtil.gsonString(aidouResult));
    }


    @Test
    public   void test2(){
        TopicListRequest request=new TopicListRequest();
        request.setType(1);
        List<TopicItemRespone> topicItemResponeList = topicService.selectList(request);

        System.out.println(GsonUtil.gsonString(topicItemResponeList));
    }

    @Before
    public  void  init(){
        CurrentUser currentUser=new CurrentUser();
        currentUser.setId(100032L);
        currentUser.setSex(2);
        currentUser.setStatus(UserStatusEnum.CERTIFIED.getIndex());
        currentUser.setRealName(true);
        currentUser.setNikeName("张三");
        CurrentUser.set(currentUser);

    }


}
