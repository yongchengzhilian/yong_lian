package com.aidou.api.service.impl;

import com.aidou.api.enums.LineSourceType;
import com.aidou.api.service.LineService;
import com.aidou.api.service.UserRelationService;
import com.aidou.api.service.WeChatMessagePushService;
import com.aidou.dao.entity.TbUser;
import com.aidou.dao.entity.TbUserExample;
import com.aidou.dao.entity.TbUserRelation;
import com.aidou.dao.entity.TbUserRelationExample;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.dao.mapper.TbUserRelationMapper;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.entity.wechat.WeChatMessageModel;
import com.aidou.util.enums.UserStatusEnum;
import com.aidou.util.tool.DateUtil;
import com.aidou.util.tool.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/14
 * Description:
 */
@Service
public class UserRelationServiceImpl implements UserRelationService {

    @Autowired
    private TbUserRelationMapper tbUserRelationMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private LineService lineService;

    @Resource
    private WeChatMessagePushService  weChatMessagePushService;


    @Override
    public void addRelation(Long sud, Long uid) {
        if (sud.intValue() == 88888) {
            return;
        }
        TbUserRelation tbuserRalation = new TbUserRelation();
        tbuserRalation.setCreated(new Date());
        tbuserRalation.setUpdated(new Date());
        tbuserRalation.setId(idWorker.nextId());
        tbuserRalation.setsUserId(sud);
        tbuserRalation.settUserId(uid);
        tbUserRelationMapper.insertSelective(tbuserRalation);
        //判断分享者的分享数量
        if (isAddRedLine(sud)) {
            //增加红线
            lineService.addLine(sud, LineSourceType.SHARE_GIFT, 1);
            WeChatMessageModel request = new WeChatMessageModel();
            request.setRemarks(DateUtil.getDateStr());
            request.setResult("成功分享用户,红线已放入您的账户");
            request.setPage("pages/login/index/index");
            request.setUid(sud);
            weChatMessagePushService.reminderSentSuccessfully(request);
        }
    }

    @Override
    public Integer shareUserCount(Long uid) {
        TbUserRelationExample example = new TbUserRelationExample();
        example.createCriteria().andDpStatusEqualTo(1).andSUserIdEqualTo(uid);
        List<TbUserRelation> tbUserRelations = tbUserRelationMapper.selectByExample(example);
        return tbUserRelations.size();
    }

    @Override
    public Integer findShareRedCount(Integer size) {
        if (size == null) {
            List<TbUserRelation> tbUserRelations = getTbUserRelations();
            size = tbUserRelations.size();
        }
        if (size < 5) {
            return 0;
        }
        return size / 5;
    }

    private List<TbUserRelation> getTbUserRelations() {
        Long id = CurrentUser.get().getId();
        TbUserRelationExample example = new TbUserRelationExample();
        TbUserRelationExample.Criteria criteria = example.createCriteria();
        criteria.andDpStatusEqualTo(1).andSUserIdEqualTo(id);
        return tbUserRelationMapper.selectByExample(example);
    }

    @Override
    public Integer findShareRegisteredRedCount() {
        List<TbUserRelation> tbUserRelations = getTbUserRelations();
        List<Long> userList = tbUserRelations.stream().map((e) -> e.gettUserId()).collect(Collectors.toList());
        TbUserExample example = new TbUserExample();
        List<Integer> values = new ArrayList<>();
        values.add(UserStatusEnum.CERTIFIED.getIndex());
        values.add(UserStatusEnum.HANDS.getIndex());
        example.createCriteria().andStatusEqualTo(1).andUidIn(userList).andStatusIn(values);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        return tbUsers.size();
    }


    /**
     * 是否增加红线
     *
     * @param suid
     * @return
     */
    private boolean isAddRedLine(Long suid) {
        Integer count = shareUserCount(suid);
        if (count < 5) {
            return false;
        }
        return count % 5 == 0;

    }
}
