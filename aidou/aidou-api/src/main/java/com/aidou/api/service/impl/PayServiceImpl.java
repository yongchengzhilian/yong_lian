package com.aidou.api.service.impl;

import com.aidou.api.service.PayService;
import com.aidou.api.service.WechatAuthService;
import com.aidou.api.vo.request.wechat.WechatPayRequest;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.IdWorker;
import com.aidou.util.tool.Money;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/11
 * Description:
 *
 * @author 1
 */
@Service
public class PayServiceImpl implements PayService {


    @Autowired
    private WxPayService wxPayService;


    @Autowired
    private WechatAuthService wechatAuthService;

    @Value("${wechatPay.notifyurl}")
    private String NOTIFYURL;


    @Override
    public Map<String, Object> getJavaScriptPayInfo(WechatPayRequest wechatPayRequest,String  ip) {
        //获取商品ID
        //订单名称
        String orderSubject = "购买红线" + new Money(wechatPayRequest.getPrice()).getAmount() + "元";
        //订单金额，单位分
        Long totalAmount =wechatPayRequest.getPrice();
        //商品描述
        String goodsDesc = "甬恋红线";
        Map<String, Object> map = new HashMap<>();
        WxPayUnifiedOrderRequest prepayInfo = WxPayUnifiedOrderRequest.newBuilder()
                .openid(wechatPayRequest.getOpenId())
                .outTradeNo(wechatPayRequest.getOrderId())
                .totalFee(totalAmount.intValue())
                .body(orderSubject)
                .tradeType("JSAPI")
                .spbillCreateIp(ip)
                .notifyURL(NOTIFYURL)
                .nonceStr(getNonceStr())
                .detail(goodsDesc)
                .build();
        try {
            Map<String, String> payInfo = wxPayService.getPayInfo(prepayInfo);
            map.put("result", true);
            map.put("data", payInfo);
        } catch (WxPayException e) {
            map.put("result", false);
            map.put("data", e.getErrCodeDes());
            e.printStackTrace();
        }
        return map;
    }



    /**
     * 获得微信支付随机码
     *
     * @return Date:2017年12月4日上午9:50:48
     * @author 吉文剑
     */
    private  String getNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }



}
