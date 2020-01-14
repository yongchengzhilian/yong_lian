package com.aidou.api.service.impl;

import com.aidou.api.common.cache.RedisDao;
import com.aidou.api.common.httpclient.HttpAPIService;
import com.aidou.api.service.IdService;
import com.aidou.api.service.UserRelationService;
import com.aidou.api.service.WechatAuthService;
import com.aidou.api.vo.wechat.JSAccessToken;
import com.aidou.api.vo.WechatInfo;
import com.aidou.dao.entity.TbUser;
import com.aidou.dao.entity.TbWechatAuth;
import com.aidou.dao.entity.TbWechatAuthExample;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.dao.mapper.TbWechatAuthMapper;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.entity.wechat.UserAccessToken;
import com.aidou.util.entity.wechat.WechatAuthInfo;
import com.aidou.util.enums.AppStatusEnum;
import com.aidou.util.enums.UserStatusEnum;
import com.aidou.util.exception.BizException;
import com.aidou.util.exception.WechatAesDesryptException;
import com.aidou.util.exception.WechatTokenNotFoundException;
import com.aidou.util.tool.*;
import com.aidou.util.wechat.AesCbcUtil;
import com.aidou.util.wechat.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {

    @Autowired
    private TbWechatAuthMapper tbWechatAuthMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private IdService idService;

    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private RedisDao redisDao;

    @Resource
    private HttpAPIService httpAPIService;


    @Autowired
    private UserRelationService userRelationService;


    @Transactional(rollbackFor = BizException.class)
    @Override
    public AidouResult login(WechatAuthInfo wechatAuthInfo) throws WechatTokenNotFoundException, WechatAesDesryptException {
        //获取微信 openID
        UserAccessToken userAccessToken = WechatUtil.getUserAccessToken(wechatAuthInfo.getCode());
        if (userAccessToken == null) {
            throw new WechatTokenNotFoundException();
        }
        //获取微信openId
        //判断新用户或者老用户
        String openid = userAccessToken.getOpenid();
        TbWechatAuthExample wechatAuth = new TbWechatAuthExample();
        wechatAuth.createCriteria().andOpenIdEqualTo(openid);
        List<TbWechatAuth> tbWechatAuths = tbWechatAuthMapper.selectByExample(wechatAuth);
        TbWechatAuth wechatUser = null;
        if (tbWechatAuths.isEmpty()) {
            //新用户
            WechatInfo wechatInfo;
            try {
                String decrypt = AesCbcUtil.decrypt(wechatAuthInfo.getEncryptedData(), userAccessToken.getSession_key(), wechatAuthInfo.getIv(), "UTF-8");
                wechatInfo = GsonUtil.gsonToBean(decrypt, WechatInfo.class);
            } catch (Exception e) {
                e.printStackTrace();
                //通过openID获取用户信息
                return AidouResult.error("小程序解密失败");
            }
            //绑定关系
            long id = idService.getUid();
             wechatUser = new TbWechatAuth();
            wechatUser.setUserId(id);
            wechatUser.setCreateTime(new Date());
            wechatUser.setUnionId(wechatInfo.getUnionId());
            wechatUser.setOpenId(wechatInfo.getOpenId());
            tbWechatAuthMapper.insertSelective(wechatUser);
            //创建基本信息表
            TbUser tbUser = new TbUser();
            tbUser.setUid(id);
            tbUser.setLastArticle(new Date());
            tbUser.setAvatarurl(wechatInfo.getAvatarUrl());
            tbUser.setNikename(EmojiUtil.emojiConverterToAlias(wechatInfo.getNickName()));
            tbUser.setCreated(new Date());
            tbUser.setUpdated(new Date());
            tbUser.setLastTime(new Date());
            tbUser.setStatus(UserStatusEnum.FULL_DATA.getIndex());
            //1男  2女
            tbUser.setSex(wechatInfo.getGender() == 1 ? 1 : 2);
            tbUserMapper.insertSelective(tbUser);
            //分享关系
            userRelationService.addRelation(wechatAuthInfo.getUid(), id);
        } else {
            //老用户
            wechatUser = tbWechatAuths.get(0);
            //判断用户是否注销
            TbUser tbUser = tbUserMapper.selectByPrimaryKey(wechatUser.getUserId());
            if (tbUser.getAccountStatus() == AppStatusEnum.ACCOUNT_STATUS5.getIndex()) {
                tbUser.setAccountStatus(AppStatusEnum.ACCOUNT_STATUS1.getIndex());
                tbUser.setAccountStatusName(AppStatusEnum.ACCOUNT_STATUS1.getName());
                tbUser.setUpdated(new Date());
                tbUserMapper.updateByPrimaryKeySelective(tbUser);
            }
        }
        String token = JwtUtils.createToken(jwtConfig, wechatUser.getUserId());
        return AidouResult.success(token);
    }

    @Override
    public String deciphering(String encrypdata, String ivdata, String code, String source) throws WechatAesDesryptException {
        try {
            UserAccessToken userAccessToken = WechatUtil.getUserAccessToken(code);
            if (userAccessToken == null) {
                throw new WechatTokenNotFoundException();
            }
            String decrypt = AesCbcUtil.decrypt(encrypdata, userAccessToken.getSession_key(), ivdata, "UTF-8");
            return decrypt;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WechatAesDesryptException("小程序解密失败");
        }
    }

    @Override
    public void saveMessageId(String id) {
        System.out.println("CurrentUser.get()" + CurrentUser.get());
//        if (CurrentUser.get() != null) {
//            //保存FromID 7天
//            AppletFromIdUtil.saveOrupdateFormId(CurrentUser.get().getId(), id, redisDao);
//        }

    }

    @Override
    public String findOpenIdByUid(Long uid) {
        TbWechatAuthExample example = new TbWechatAuthExample();
        example.createCriteria().andUserIdEqualTo(uid);
        List<TbWechatAuth> tbWechatAuths = tbWechatAuthMapper.selectByExample(example);
        if (tbWechatAuths.isEmpty()) {
            return null;
        }

        return tbWechatAuths.get(0).getOpenId();
    }

    @Override
    public String jsAccessToken() {
        String jsAccessToken = redisDao.getValue("jsAccessToken");
        System.out.println("jsAccessToken" + jsAccessToken);
        if (jsAccessToken != null) {
            return jsAccessToken;
        }
        try {
            String s = httpAPIService.doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb55e464ea69e3136&secret=1fab3b5adc921e73d3cde94ac1796cca");
            JSAccessToken jsAccessToken1 = GsonUtil.gsonToBean(s, JSAccessToken.class);
            redisDao.setKey("jsAccessToken", jsAccessToken1.getAccess_token(), 110);
            return jsAccessToken1.getAccess_token();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
