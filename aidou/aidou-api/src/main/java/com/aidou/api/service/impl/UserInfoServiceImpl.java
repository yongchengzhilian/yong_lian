package com.aidou.api.service.impl;

import com.aidou.api.enums.CertificationStatusEnum;
import com.aidou.api.service.LineService;
import com.aidou.api.service.UserInfoService;
import com.aidou.api.vo.respone.JsPayUserInfoRespone;
import com.aidou.dao.entity.*;
import com.aidou.dao.mapper.TbOrderMapper;
import com.aidou.dao.mapper.TbUserContentMapper;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.enums.AppStatusEnum;
import com.aidou.util.enums.UserStatusEnum;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.EmojiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 用户资料相关
 * Created by yingjiafeng on 2019/5/21.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private TbUserContentMapper tbUserContentMapper;

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Resource
    private LineService lineService;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUserContent findUserContentByUid(Long uid) throws BizException {
        Optional.ofNullable(uid).orElseThrow(NullPointerException::new);
        TbUserContent tbUserContent = tbUserContentMapper.selectByPrimaryKey(uid);
        if (tbUserContent == null) {
            return new TbUserContent();
        }
        return tbUserContent;
    }


    @Override
    public List<TbUser> findUserListByIds(List<Long> ids) throws BizException {
        TbUserExample example = new TbUserExample();
        example.createCriteria().andUidIn(ids);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        return tbUsers;
    }

    @Override
    public int updateUserStatus(Long id, UserStatusEnum userStatusEnum) throws BizException {
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(id);
        Optional.ofNullable(tbUser).orElseThrow(() -> new BizException("用户不存在"));
        tbUser.setStatus(userStatusEnum.getIndex());
        tbUser.setUpdated(new Date());
        return tbUserMapper.updateByPrimaryKey(tbUser);
    }

    @Override
    public void addSchoolCode(String code) throws BizException {
        Optional.ofNullable(code).orElseThrow(() -> new BizException("学信网在线验证码不能为空"));
        TbUser tbUser = new TbUser();
        tbUser.setUid(CurrentUser.get().getId());
        tbUser.setVerificationCode(code);
        tbUser.setUpdated(new Date());
        tbUser.setSchoolVerification(CertificationStatusEnum.CERTIFICATION.getIndex());
        int i = tbUserMapper.updateByPrimaryKeySelective(tbUser);
        System.out.println(i);
    }

    @Override
    public int updateUser(TbUser tbUser) throws BizException {
        tbUser.setUpdated(new Date());
        return tbUserMapper.updateByPrimaryKeySelective(tbUser);
    }

    @Override
    public TbUser findUserById(Long uid) {
        return tbUserMapper.selectByPrimaryKey(uid);
    }

    @Override
    public JsPayUserInfoRespone selectPayUserInfo(Long userId) {
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(userId);
        Optional.ofNullable(tbUser).orElseThrow(() -> new BizException("用户不存在"));
        JsPayUserInfoRespone jsPayUserInfoRespone = new JsPayUserInfoRespone();
        jsPayUserInfoRespone.setNnnnn(EmojiUtil.emojiConverterUnicodeStr(tbUser.getNikename()));
        jsPayUserInfoRespone.setFfff(tbUser.getAvatarurl());
        TbOrderExample example = new TbOrderExample();
        example.createCriteria().andDpStatusEqualTo(AppStatusEnum.ORDER_STATUS2.getIndex().byteValue()).andUseridEqualTo(userId);
        int count = tbOrderMapper.countByExample(example);
        jsPayUserInfoRespone.setCccc(count);
        Integer redCountByUid = lineService.findRedCountByUid(userId);
        jsPayUserInfoRespone.setYyyy(redCountByUid);
        return jsPayUserInfoRespone;
    }

}
