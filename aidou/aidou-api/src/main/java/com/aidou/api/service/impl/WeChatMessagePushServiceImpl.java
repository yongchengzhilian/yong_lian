package com.aidou.api.service.impl;

import com.aidou.api.common.cache.RedisDao;
import com.aidou.api.common.cache.RedisKey;
import com.aidou.api.common.httpclient.HttpAPIService;
import com.aidou.api.common.httpclient.HttpResult;
import com.aidou.api.enums.WechatTemplateEnum;
import com.aidou.api.service.WeChatMessagePushService;
import com.aidou.api.service.WechatAuthService;
import com.aidou.api.vo.request.QrCodeRequest;
import com.aidou.api.vo.request.wechat.TemplateParam;
import com.aidou.api.vo.request.wechat.WechatPushMessageRequest;
import com.aidou.api.vo.respone.WxAccountTokenRespone;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.wechat.WeChatMessageModel;
import com.aidou.util.entity.wechat.WecharMessageResult;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.DateUtil;
import com.aidou.util.tool.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingjiafeng on 2019/6/23.
 */
@Service
public class WeChatMessagePushServiceImpl implements WeChatMessagePushService {


    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.secret}")
    private String secret;

    @Resource
    private RedisDao redisDao;

    @Autowired
    private HttpAPIService httpAPIService;

    @Autowired
    private WechatAuthService wechatAuthService;

    @Override
    public String getAccessToken() {
        //缓存获取
        System.out.println("appid" + appid);
        String value = redisDao.getValue(RedisKey.ACCESS_TOKEN.toString());
        if (StringUtils.isEmpty(value)) {
            Map<String, Object> map = new HashMap<>();
            map.put("appid", appid);
            map.put("secret", secret);
            String json = null;
            try {
                json = httpAPIService.doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential", map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!StringUtils.isEmpty(json)) {
                WxAccountTokenRespone tokenRespone = GsonUtil.gsonToBean(json, WxAccountTokenRespone.class);
                redisDao.setKey(RedisKey.ACCESS_TOKEN.toString(), tokenRespone.getAccess_token(), 119);
                value = tokenRespone.getAccess_token();
            }
        }
        return value;
    }

    @Override
    public void checkResultMessage(WeChatMessageModel weChatMessageModel) throws BizException {
        //审核结果通知
        List<TemplateParam> paras=new ArrayList<>();
        paras.add(new TemplateParam("phrase2",weChatMessageModel.getResult()));
        paras.add(new TemplateParam("thing3",weChatMessageModel.getRemarks()));
        initMessage(weChatMessageModel.getUid(), weChatMessageModel.getPage(), paras, WechatTemplateEnum.TEMPLATE_1.getIndex());
    }

    @Override
    public void matchmakingResultMessage(WeChatMessageModel weChatMessageModel) throws BizException {
        //牵线结果通知
        List<TemplateParam> paras=new ArrayList<>();
        paras.add(new TemplateParam("phrase1",weChatMessageModel.getResult()));
        paras.add(new TemplateParam("thing2",weChatMessageModel.getRemarks()));
        initMessage(weChatMessageModel.getUid(), weChatMessageModel.getPage(), paras, WechatTemplateEnum.TEMPLATE_2.getIndex());
    }

    @Override
    public void matchFailedResultMessage(WeChatMessageModel weChatMessageModel) throws BizException {
        //匹配失败通知
        List<TemplateParam> paras=new ArrayList<>();
        paras.add(new TemplateParam("phrase2",weChatMessageModel.getResult()));
        paras.add(new TemplateParam("thing3",weChatMessageModel.getRemarks()));
        initMessage(weChatMessageModel.getUid(), weChatMessageModel.getPage(), paras, WechatTemplateEnum.TEMPLATE_7.getIndex());
    }

    @Override
    public void matchmakingRequestMessage(WeChatMessageModel weChatMessageModel) throws BizException {
        //牵线请求通知
        List<TemplateParam> paras=new ArrayList<>();
        paras.add(new TemplateParam("name1",weChatMessageModel.getResult()));
        paras.add(new TemplateParam("date2", DateUtil.getDateStr()));
        initMessage(weChatMessageModel.getUid(), weChatMessageModel.getPage(), paras, WechatTemplateEnum.TEMPLATE_3.getIndex());
    }

    @Override
    public void leaveMessageMessage(WeChatMessageModel weChatMessageModel) throws BizException {
        //留言回复通知
        List<TemplateParam> paras=new ArrayList<>();
        paras.add(new TemplateParam("name2",weChatMessageModel.getResult()));
        paras.add(new TemplateParam("thing1",weChatMessageModel.getRemarks()));
        initMessage(weChatMessageModel.getUid(), weChatMessageModel.getPage(), paras, WechatTemplateEnum.TEMPLATE_4.getIndex());
    }

    @Override
    public void activityessage(WeChatMessageModel weChatMessageModel) throws BizException {
        //活动报名通知
//        MessageCheckCheckBean dataCheckBean = new MessageCheckCheckBean();
//        KeywordBean thing1 = new KeywordBean();
//        thing1.setValue(weChatMessageModel.getActivityTitle());
//        dataCheckBean.setThing1(thing1);
//        KeywordBean thing2 = new KeywordBean();
//        thing2.setValue(weChatMessageModel.getActivityAddress());
//        dataCheckBean.setThing2(thing2);
//        KeywordBean time3=new KeywordBean();
//        time3.setValue(weChatMessageModel.getActivityTime());
//        dataCheckBean.setTime3(time3);
//        initMessage(weChatMessageModel.getUid(), weChatMessageModel.getPage(), dataCheckBean, WechatTemplateEnum.TEMPLATE_5.getIndex());
    }

    @Override
    public void reminderSentSuccessfully(WeChatMessageModel weChatMessageModel) throws BizException {
        //提醒发送成功通知
        //使⽤用场景：A⽤用户邀请了了5个⽤用户赠送⼀一条红线， 或者A⽤用户邀请点好友资料料 提交审核通过
        List<TemplateParam> paras=new ArrayList<>();
        paras.add(new TemplateParam("thing4",weChatMessageModel.getResult()));
        paras.add(new TemplateParam("time5",weChatMessageModel.getRemarks()));
        initMessage(weChatMessageModel.getUid(), weChatMessageModel.getPage(), paras, WechatTemplateEnum.TEMPLATE_6.getIndex());
    }


    @Override
    public AidouResult createdQRCode(QrCodeRequest qrCodeRequest) {
        try {
            //获取手机号
            HttpResult httpResult = httpAPIService.doPostDownImageToBase64("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + getAccessToken(), GsonUtil.toJsonString(qrCodeRequest));
            if (httpResult.getCode() == 200) {
                return AidouResult.success(httpResult.getBody());
            } else {
                return AidouResult.error("转换二维码图片失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private void initMessage(Long uid, String page, List<TemplateParam>  paras, String templateId) {
        // pages/login/index/index
        String openId = wechatAuthService.findOpenIdByUid(uid);
        if (StringUtils.isEmpty(openId)) {
            System.out.println("该用户不存在" + uid);
            return;
        }
        WechatPushMessageRequest messageRequest = new WechatPushMessageRequest();
        messageRequest.setTouser(openId);
        messageRequest.setPage(page);
        messageRequest.setTemplate_id(templateId);
        messageRequest.setTemplateParamList(paras);
        int i = httpSendMessage(messageRequest);
        if (i <= 0) {
            System.out.println("消息发送失败" + uid);
        }
    }


    private int httpSendMessage(WechatPushMessageRequest messageRequest) throws BizException {
        try {
            HttpResult result = httpAPIService.doPost(getWechatUrl(), messageRequest.toJSON());
            String body = result.getBody();
            System.out.println(body);
            if (!org.apache.commons.lang3.StringUtils.isEmpty(body)) {
                WecharMessageResult wecharMessageResult = GsonUtil.gsonToBean(body, WecharMessageResult.class);
                if (wecharMessageResult.getErrcode() == 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    private String getWechatUrl() {
        String accessToken = getAccessToken();
        return "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken;
    }

}
