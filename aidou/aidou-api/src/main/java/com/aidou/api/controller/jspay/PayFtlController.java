package com.aidou.api.controller.jspay;

import com.aidou.api.service.JsPayService;
import com.aidou.api.service.UserInfoService;
import com.aidou.api.vo.respone.JsPayUserInfoRespone;
import com.aidou.util.entity.AidouResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RequestMapping("jsPay/")
@Controller
public class PayFtlController {

    @Resource
    private JsPayService jsPayService;

    @Resource
    private UserInfoService userInfoService;


    @GetMapping(value = "getOAuth")
    public String getOAuth(HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");//获取微信服务器授权返回的code值
        Long userId = -1L;
        if (!StringUtils.isEmpty(code)) {
            userId = jsPayService.getUserOAuth(code);
        }
        return "redirect:https://www.aidou.online/pay?userId=" + userId;
    }


    @GetMapping(value = "selectPayUserInfo/{userId}")
    @ResponseBody
    public AidouResult selectPayUserInfo(@PathVariable Long userId) {
        JsPayUserInfoRespone jsPayUserInfoRespone = userInfoService.selectPayUserInfo(userId);
        return AidouResult.success(jsPayUserInfoRespone);
    }

}
