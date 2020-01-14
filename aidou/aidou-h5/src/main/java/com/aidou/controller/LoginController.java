package com.aidou.controller;

import com.aidou.service.RmUserService;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.tool.ExceptionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yingjiafeng on 2019/4/29.
 */

@Api(tags = "登陆")
@RestController
public class LoginController  extends BaseController {

    @Autowired
    private RmUserService rmUserService;



    @ApiOperation(value = "登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "验证码",required = true),
            @ApiImplicitParam(name = "mobile",value = "手机号",required = true)
    })
    @PostMapping("/login")
    public AidouResult  login(String code, String mobile, HttpServletRequest servletRequest){
        //红娘短信登陆
        String ipAddress = getIPAddress(servletRequest);
        try {
           return  rmUserService.login(ipAddress,mobile,code);

        }catch (Exception e){
                  e.printStackTrace();
                  return AidouResult.error(ExceptionUtil.getExceptionAllinformation_01(e));
        }




    }


}
