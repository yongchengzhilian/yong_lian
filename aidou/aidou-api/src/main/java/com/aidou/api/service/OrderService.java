package com.aidou.api.service;

import com.aidou.api.vo.request.OrderCreateRequest;
import com.aidou.api.vo.request.wechat.WechatPayRequest;
import com.aidou.api.vo.wechat.PayFlowEntity;

import java.util.Map;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/11
 * Description:
 * @author 1
 */
public interface OrderService {

    /**
     * 订单支付
     * @param orderCreateRequest
     */
    Map<String, Object> orderPay(OrderCreateRequest orderCreateRequest,String  ip);

    /**
     * 订单回调
     * @param entity
     */
    void PayResult(PayFlowEntity entity);

    /**
     * 获取用户订单数量
     * @return
     */
    Integer orderNum(Long uid);

}
