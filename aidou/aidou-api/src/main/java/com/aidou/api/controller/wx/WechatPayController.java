package com.aidou.api.controller.wx;

import com.aidou.api.controller.BaseController;
import com.aidou.api.service.OrderService;
import com.aidou.api.vo.wechat.PayFlowEntity;
import com.aidou.util.tool.GsonUtil;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.service.WxPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/10
 * Description:
 */

@Slf4j
@Api(tags = "微信支付")
@RestController
@RequestMapping("/wxPay")
public class WechatPayController extends BaseController {

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private OrderService  orderService;


    /**
     * 微信支付回调
     *
     * @param request
     */
    @ApiOperation(value = "微信支付回调", notes = "微信支付回调")
    @RequestMapping(value = "/notifyCallback")
    public String notifyCallback(HttpServletRequest request, HttpServletResponse response) {

        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlResult);
            // 结果正确 outTradeNo
            String orderId = notifyResult.getOutTradeNo();
            String tradeNo = notifyResult.getTransactionId();
//            Integer totalFee = notifyResult.getTotalFee();//分
            if("SUCCESS".equals(notifyResult.getResultCode())) {
                PayFlowEntity entity = new PayFlowEntity();
                entity.setPayFee(notifyResult.getCashFee());
                entity.setPayFlowNo(orderId);
                entity.setOpenId(notifyResult.getOpenid());
                entity.setTradeNo(tradeNo);
                orderService.PayResult(entity);
            }
            //自己处理订单的业务逻辑，需要判断订单是否已经支付过，否则可能会重复调用
            return WxPayNotifyResponse.success("成功");
        } catch (Exception e) {
            log.error("微信回调结果异常,异常原因{}", e.getMessage());
            return WxPayNotifyResponse.success("code:"+9999+"微信回调结果异常,异常原因:"+e.getMessage());
        }
    }
}
