package com.aidou.api.service.impl;

import com.aidou.api.common.httpclient.HttpAPIService;
import com.aidou.api.common.httpclient.HttpResult;
import com.aidou.api.service.WeChatContentCheckService;
import com.aidou.api.service.WeChatMessagePushService;
import com.aidou.api.vo.request.MsgSecCheckRequest;
import com.aidou.api.vo.request.wechat.WechatRespone;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class WeChatContentCheckServiceImpl implements WeChatContentCheckService {

    @Autowired
    private WeChatMessagePushService weChatMessagePushService;
    @Autowired
    private HttpAPIService  httpAPIService;


    @Override
    public boolean msgSecCheck(String content)  throws BizException {
        String accessToken = weChatMessagePushService.getAccessToken();
        try {
            HttpResult httpResult = httpAPIService.doPost("https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" + accessToken,new MsgSecCheckRequest(content).toString());
            if (httpResult.getCode()!=200){
                return false;
            }
            WechatRespone wechatRespone = GsonUtil.gsonToBean(httpResult.getBody(), WechatRespone.class);
            if (wechatRespone.getErrcode()!=0){
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
