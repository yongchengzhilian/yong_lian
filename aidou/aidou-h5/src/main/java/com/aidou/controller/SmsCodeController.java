package com.aidou.controller;

import com.aidou.service.SmsCodeService;
import com.aidou.util.entity.AidouResult;
import com.aidou.vo.SmsCodeRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by guoyi on 2017/4/24.
 */

@Api(tags="验证码")
@RequestMapping("sms")
@RestController
public class SmsCodeController extends BaseController {

    @Autowired
    private SmsCodeService smsCodeService;




    @ApiOperation(value = "验证码发送")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true),
            @ApiImplicitParam(name = "type", value = "验证码类型(LOGIN)", required = true)
    }
    )
    @RequestMapping(value = "/smsCode", method = RequestMethod.POST)
    public AidouResult requestSmsCode(SmsCodeRequest smsCodeRequest, HttpServletRequest servletRequest) {
        try {
            String ipAddress = getIPAddress(servletRequest);
            System.out.println("ipAddress:"+ipAddress);
             return smsCodeService.makeAndSend(smsCodeRequest.getMobile(), smsCodeRequest.getType(), ipAddress);
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return AidouResult.error("短信发送失败");
        }
    }


}
