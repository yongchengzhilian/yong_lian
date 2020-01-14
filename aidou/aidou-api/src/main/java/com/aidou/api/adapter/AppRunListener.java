package com.aidou.api.adapter;

import com.aidou.api.common.cache.RedisDao;
import com.aidou.dao.mapper.TbCommentMapper;
import com.aidou.dao.mapper.TbTopicMapper;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class AppRunListener  implements ApplicationListener<ContextRefreshedEvent> {

//    @Resource
//    private RedisDao redisDao;
//
//    @Resource
//    private TbTopicMapper tbTopicMapper;
//
//    @Resource
//    private TbCommentMapper  tbCommentMapper;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
             //获取话题

             //获取评论点赞



    }
}
