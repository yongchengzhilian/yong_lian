package com.aidou.api.service.impl;

import com.aidou.api.common.httpclient.HttpAPIService;
import com.aidou.api.service.JsPayService;
import com.aidou.api.service.WechatAuthService;
import com.aidou.api.vo.respone.JSWxTokenOpenIdRespone;
import com.aidou.api.vo.wechat.JsUserInfoRespone;
import com.aidou.dao.entity.TbWechatAuth;
import com.aidou.dao.entity.TbWechatAuthExample;
import com.aidou.dao.mapper.TbWechatAuthMapper;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.GsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class JsPayServiceImpl implements JsPayService {

    @Resource
    private HttpAPIService httpAPIService;

    @Resource
    private TbWechatAuthMapper  tbWechatAuthMapper;


    @Override
    public Long getUserOAuth(String code) {

        String doGet = null;
        try {
            doGet = httpAPIService.doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxb55e464ea69e3136&secret=1fab3b5adc921e73d3cde94ac1796cca&code=" + code + "&grant_type=authorization_code");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("获取code数据OPENID"+doGet);
        JSWxTokenOpenIdRespone jsWxTokenOpenIdRespone = GsonUtil.gsonToBean(doGet, JSWxTokenOpenIdRespone.class);
        Optional.ofNullable(jsWxTokenOpenIdRespone.getUnionid()).orElseThrow(() -> new BizException("union ID 无法获取"));
        Long userId=-1L;
            //根据 unionid获取用户uid
            TbWechatAuthExample exmaple=new TbWechatAuthExample();
            exmaple.createCriteria().andUnionIdEqualTo(jsWxTokenOpenIdRespone.getUnionid());
            List<TbWechatAuth> tbWechatAuths = tbWechatAuthMapper.selectByExample(exmaple);
            if (!tbWechatAuths.isEmpty()){
                userId=tbWechatAuths.get(0).getUserId();
            }

        return userId;

    }
}
