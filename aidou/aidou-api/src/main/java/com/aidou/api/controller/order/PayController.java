package com.aidou.api.controller.order;


import com.aidou.api.controller.BaseController;
import com.aidou.api.service.OrderService;
import com.aidou.api.vo.request.OrderCreateRequest;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.tool.ValidationUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Slf4j
@Api(tags = "订单")
@RestController
@RequestMapping("/order")
public class PayController  extends BaseController {

    @Autowired
    private OrderService orderService;


    @ApiOperation(value = "订单支付", notes = "订单支付")
    @PostMapping(value = "/pay")
    public AidouResult orderPay(@RequestBody OrderCreateRequest orderCreateRequest, HttpServletRequest  servletRequest) {
        ValidationUtils.validate(orderCreateRequest);
        String ipAddress = getIPAddress(servletRequest);
        Map<String, Object> stringObjectMap = orderService.orderPay(orderCreateRequest,ipAddress);
        return AidouResult.success(stringObjectMap);
    }


    @ApiOperation(value = "获取用户订单数", notes = "获取用户订单数")
    @PostMapping(value = "/orderNum")
    public AidouResult orderNum() {
        Integer  num = orderService.orderNum(CurrentUser.get().getId());
        return AidouResult.success(num);
    }


}
