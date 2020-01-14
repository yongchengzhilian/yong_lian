package com.aidou.api.controller.wx;

import com.aidou.api.controller.BaseController;
import com.aidou.api.service.WechatService;
import com.aidou.api.util.SignUtil;
import com.aidou.api.vo.respone.WechatEventRespone;
import com.aidou.api.vo.wechat.WechatCustomerRespone;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.wechat.WechatAuthInfo;
import com.aidou.util.exception.WechatAesDesryptException;
import com.aidou.util.exception.WechatTokenNotFoundException;
import com.aidou.util.tool.GsonUtil;
import com.aidou.util.wechat.StreamUtil;
import com.github.pagehelper.util.StringUtil;
import com.thoughtworks.xstream.XStream;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/8/27 9:32
 */

@Api(tags = "微信验证")
@RestController
@RequestMapping("/wechat")
public class WecharEventController extends BaseController {

    @Resource
    private WechatService wechatService;

    @ApiOperation(value = "微信token&回调事件", notes = "微信token&回调事件")
    @RequestMapping(value = "/token/verification")
    public void application(HttpServletResponse servletResponse, HttpServletRequest servletRequest) throws Exception {
        String inputStreamToString = StreamUtil.inputStreamToString(servletRequest.getInputStream());
        if (inputStreamToString.indexOf("ToUserName")>0){
            //小程序客服消息
            System.out.println(inputStreamToString);
            WechatCustomerRespone wechatCustomerRespone = GsonUtil.gsonToBean(inputStreamToString, WechatCustomerRespone.class);
            wechatService.smallAppMessageSend(wechatCustomerRespone);
        }else {
            wechatService.tokenInVerification(servletRequest, servletResponse);
        }
    }
}
