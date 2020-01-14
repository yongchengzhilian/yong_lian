package com.aidou.api.service;

import com.aidou.api.vo.wechat.WechatCustomerRespone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WechatService {


    /**
     * 微信服务器验证
     * @param request
     * @param response
     */
    void tokenInVerification(HttpServletRequest request, HttpServletResponse response);

    /**
     * 发送客服消息
     * @param wechatCustomerRespone
     */
    void smallAppMessageSend(WechatCustomerRespone wechatCustomerRespone);

}
