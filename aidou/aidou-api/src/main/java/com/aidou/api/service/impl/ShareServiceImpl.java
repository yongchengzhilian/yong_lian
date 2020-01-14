package com.aidou.api.service.impl;

import com.aidou.api.service.ShareService;
import com.aidou.api.service.UserRelationService;
import com.aidou.api.vo.respone.ShareUserRespone;
import com.aidou.dao.entity.TbUser;
import com.aidou.dao.entity.TbUserExample;
import com.aidou.dao.entity.TbUserRelation;
import com.aidou.dao.entity.TbUserRelationExample;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.dao.mapper.TbUserRelationMapper;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.enums.UserStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/14
 * Description:
 */
@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private TbUserRelationMapper tbUserRelationMapper;
    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private UserRelationService  userRelationService;


    @Override
    public Map<String, Object> info() {
        Map<String, Object> map = new HashMap<>();
        Long id = CurrentUser.get().getId();
        TbUserRelationExample example = new TbUserRelationExample();
        TbUserRelationExample.Criteria criteria = example.createCriteria();
        criteria.andDpStatusEqualTo(1).andSUserIdEqualTo(id);
        List<TbUserRelation> tbUserRelations = tbUserRelationMapper.selectByExample(example);
        List<ShareUserRespone> loginUserList = new ArrayList<>();
        List<ShareUserRespone> registeredList = new ArrayList<>();
        map.put("loginUserList",loginUserList);
        map.put("registeredList",registeredList);
        if (tbUserRelations.isEmpty()) {
            return map;
        }
        List<Long> userId = tbUserRelations.stream().map((e) -> e.gettUserId()).collect(Collectors.toList());
        TbUserExample userExample = new TbUserExample();
        userExample.createCriteria().andAccountStatusEqualTo(1).andUidIn(userId);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(userExample);
        for (TbUserRelation tbUserRelation : tbUserRelations) {
            //判断用户是否完善资料
            Long tUid = tbUserRelation.gettUserId();
            Optional<TbUser> first = tbUsers.stream().filter((e) -> e.getUid().longValue() == tUid.longValue()).findFirst();
            if (first.isPresent()){
                TbUser tbUser = first.get();
                ShareUserRespone item=new ShareUserRespone();
                item.setFace(tbUser.getAvatarurl());
                item.setNikeName(tbUser.getNikename());
                item.setUid(tUid);
                if (tbUser.getStatus()== UserStatusEnum.CERTIFIED.getIndex() ||tbUser.getStatus()== UserStatusEnum.HANDS.getIndex()){
                    //资料完成
                    registeredList.add(item);
                }
                loginUserList.add(item);
            }
        }
        //获取用户分享得到的红线数量
        Integer ocunt= userRelationService.findShareRedCount(loginUserList.size());
        map.put("redCount",ocunt);
        //获取注册获取的红线数量
        Integer reGisterCount= userRelationService.findShareRegisteredRedCount();
        map.put("reGisterCount",reGisterCount);
        return map;
    }
}
