package com.aidou.api.timer;

import com.aidou.api.adapter.AppDateInitConfig;
import com.aidou.api.service.FriendApplicationService;
import com.aidou.api.service.TopicService;
import com.aidou.api.service.WeChatMessagePushService;
import com.aidou.dao.entity.TbFriendApplication;
import com.aidou.dao.entity.TbFriendApplicationExample;
import com.aidou.dao.entity.TbUser;
import com.aidou.dao.mapper.TbFriendApplicationMapper;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.util.entity.wechat.WeChatMessageModel;
import com.aidou.util.enums.AppStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 资料完善   单身
 * 每周一0点 系统赠送2条红线 收回其他定时赠送红线
 * Created by yingjiafeng on 2019/5/17.
 */
@Component
public class UserLastTimeExe {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbFriendApplicationMapper tbFriendApplicationMapper;

    @Autowired
    private FriendApplicationService friendApplicationService;

    @Resource
    private TopicService  topicService;




    /**
     * 1小时执行一次
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void updateUserLastTime() {
        if (!AppDateInitConfig.userLastTimeMap.isEmpty()) {
            AppDateInitConfig.userLastTimeMap.forEach((x, y) -> {
                TbUser tbUser = tbUserMapper.selectByPrimaryKey(x);
                tbUser.setUpdated(new Date());
                tbUser.setLastTime(y);
                tbUserMapper.updateByPrimaryKey(tbUser);
            });

        } else {
            System.out.println("没有登录用户");
        }
        //扫描牵手申请 超过24小时红线退回
        TbFriendApplicationExample example = new TbFriendApplicationExample();
        TbFriendApplicationExample.Criteria criteria = example.createCriteria();
        List<Integer> values = new ArrayList<>();
        values.add(AppStatusEnum.APPLICATION_STATUS1.getIndex());
        values.add(AppStatusEnum.APPLICATION_STATUS4.getIndex());
        criteria.andStatusIn(values);
        criteria.andIslikeEqualTo(false);
        criteria.andLineBackLessThanOrEqualTo(new Date());
        List<TbFriendApplication> tbFriendApplications = tbFriendApplicationMapper.selectByExample(example);
        //退回红线
        friendApplicationService.goBackToUser(tbFriendApplications);
        AppDateInitConfig.userLastTimeMap.clear();
        //刷新redis 点赞  浏览  评论  数量到 数据库
        topicService.refreshDataByCachaToDb();
    }


}
