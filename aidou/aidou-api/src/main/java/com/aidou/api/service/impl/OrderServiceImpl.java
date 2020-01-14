package com.aidou.api.service.impl;

import com.aidou.api.enums.LineSourceType;
import com.aidou.api.service.*;
import com.aidou.api.vo.request.OrderCreateRequest;
import com.aidou.api.vo.request.wechat.WechatPayRequest;
import com.aidou.api.vo.wechat.PayFlowEntity;
import com.aidou.dao.entity.TbOrder;
import com.aidou.dao.entity.TbOrderError;
import com.aidou.dao.entity.TbOrderExample;
import com.aidou.dao.mapper.TbOrderErrorMapper;
import com.aidou.dao.mapper.TbOrderMapper;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.entity.ProductItemVo;
import com.aidou.util.entity.wechat.WeChatMessageModel;
import com.aidou.util.enums.AppStatusEnum;
import com.aidou.util.enums.ProductItemEnum;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.Money;
import com.aidou.util.tool.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOrderErrorMapper tbOrderErrorMapper;

    @Autowired
    private PayService payService;

    @Autowired
    private LineService lineService;

    @Autowired
    private WechatAuthService wechatAuthService;


    @Transactional(rollbackFor = BizException.class)
    @Override
    public Map<String, Object> orderPay(OrderCreateRequest orderCreateRequest, String ip) {
        //根据商品编号获取商品
        Long itemId = orderCreateRequest.getItemId();

        Long userId = StringUtils.isEmpty(orderCreateRequest.getUserId()) ? CurrentUser.get().getId() : orderCreateRequest.getUserId();

        Long productPriceByFen = ProductItemEnum.findProductPriceByFen(itemId);
        if (productPriceByFen < 0) {
            throw new BizException("商品ID不正确");
        }
        //生成订单
        String orderId;
        //前3根半价
        if (orderNum(userId) <= 2) {
            productPriceByFen = productPriceByFen / 2;
        }
        TbOrderExample example = new TbOrderExample();
        example.createCriteria().andUseridEqualTo(userId).andDpStatusEqualTo(AppStatusEnum.ORDER_STATUS1.getIndex().byteValue());
        List<TbOrder> tbOrders = tbOrderMapper.selectByExample(example);
        if (tbOrders.isEmpty()) {
            orderId = UUIDFactory.getOrderId();
            TbOrder tbOrder = new TbOrder();
            tbOrder.setOrderid(orderId);
            tbOrder.setUserid(userId);
            tbOrder.setItemid(itemId);
            tbOrder.setMoney(productPriceByFen);
            tbOrder.setCreated(new Date());
            tbOrder.setUpdated(new Date());
            tbOrder.setIp(ip);
            int count = tbOrderMapper.insertSelective(tbOrder);
            if (count < 1) {
                throw new BizException("订单添加失败");
            }
        } else {
            TbOrder tbOrder = tbOrders.get(0);
            orderId = tbOrder.getOrderid();
            productPriceByFen = tbOrder.getMoney();
        }
        String openIdByUid = wechatAuthService.findOpenIdByUid(userId);
        Optional.ofNullable(openIdByUid).orElseThrow(() -> new BizException("微信openID不存在"));
        //获取支付通道
        WechatPayRequest wechatRequest = new WechatPayRequest();
        wechatRequest.setOrderId(orderId);
        wechatRequest.setPrice(productPriceByFen);
        wechatRequest.setOpenId(openIdByUid);
        return payService.getJavaScriptPayInfo(wechatRequest, ip);
    }

    @Override
    public void PayResult(PayFlowEntity entity) {
        //获取订单号
        String payFlowNo = entity.getPayFlowNo();
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(payFlowNo);
        Optional.ofNullable(tbOrder).orElseThrow(() -> new BizException("订单不存在"));
        tbOrder.setUpdated(new Date());
        //判断支付金额是否正确
        tbOrder.setDpStatus((byte) 2);
        tbOrder.setOrderPayTime(new Date());
        tbOrder.setTradeNo(entity.getTradeNo());
        tbOrder.setRemark("订单支付成功");
        int count = tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
        if (count < 1) {
            TbOrderError tbOrderError = new TbOrderError();
            tbOrderError.setCreated(new Date());
            tbOrderError.setOrderid(payFlowNo);
            tbOrderError.setAmout(entity.getPayFee().longValue());
            tbOrderError.setRemark("更新订单出错");
            tbOrderErrorMapper.insertSelective(tbOrderError);
            throw new BizException("订单更新失败");
        }
        Long itemid = tbOrder.getItemid();
        ProductItemVo productPriceByVo = ProductItemEnum.findProductPriceByVo(itemid);
        //增加红线
        count = lineService.addLine(tbOrder.getUserid(), LineSourceType.GOLD, productPriceByVo.getNum());
        if (count < 1) {
            TbOrderError tbOrderError = new TbOrderError();
            tbOrderError.setCreated(new Date());
            tbOrderError.setOrderid(payFlowNo);
            tbOrderError.setAmout(entity.getPayFee().longValue());
            tbOrderError.setRemark("红线添加失败");
            tbOrderErrorMapper.insertSelective(tbOrderError);
            throw new BizException("红线添加失败");
        }
    }

    @Override
    public Integer orderNum(Long uid) {
        TbOrderExample example = new TbOrderExample();
        example.createCriteria().andDpStatusEqualTo(AppStatusEnum.ORDER_STATUS2.getIndex().byteValue()).andUseridEqualTo(uid);
        return tbOrderMapper.countByExample(example);
    }

}
