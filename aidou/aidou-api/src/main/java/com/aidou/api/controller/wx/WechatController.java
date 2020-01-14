package com.aidou.api.controller.wx;

import com.aidou.api.controller.BaseController;
import com.aidou.api.service.JsPayService;
import com.aidou.api.service.WeChatMessagePushService;
import com.aidou.api.service.WechatAuthService;
import com.aidou.api.vo.request.QrCodeRequest;
import com.aidou.api.vo.request.WxPhoneRequest;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.wechat.WechatAuthInfo;
import com.aidou.util.exception.WechatAesDesryptException;
import com.aidou.util.exception.WechatTokenNotFoundException;
import com.aidou.util.tool.GsonUtil;
import com.aidou.util.tool.JwtConfig;
import com.aidou.util.tool.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Slf4j
@Api(tags = "登录")
@Controller
@RequestMapping("/wx")
public class WechatController extends BaseController {

    @Autowired
    private WechatAuthService wechatAuthService;

    @Autowired
    private WeChatMessagePushService weChatMessagePushService;

    @Resource
    private JwtConfig jwtConfig;

    @Resource
    private JsPayService jsPayService;


    @GetMapping(value = "Login")
    public String doGet(HttpServletRequest request) {
        // 获取微信公众号传输过来的code,通过code可获取access_token,进而获取用户信息
        String code = request.getParameter("code");
        Long userOAuth = jsPayService.getUserOAuth(code);
        System.out.println("userOAuth"+userOAuth);
        return "redirect:https://www.aidou.online/pay?userId=" + userOAuth;
    }

    @ApiOperation(value = "刷新token", notes = "刷新token")
    @PostMapping(value = "refreshToken/{userId}")
    public @ResponseBody AidouResult refreshToken(@PathVariable Long userId) {
        String token = JwtUtils.createToken(jwtConfig, userId);
        System.out.println("新建的token"+token);
        return AidouResult.success(token);
    }


    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "微信登录code", required = true),
            @ApiImplicitParam(name = "encryptedData", value = "主体加密数据", required = true),
            @ApiImplicitParam(name = "iv", value = "偏移量", required = true)
    }
    )
    @PostMapping(value = "login")
    public @ResponseBody AidouResult login(@Valid @RequestBody WechatAuthInfo wechatAuthInfo, BindingResult bindingResult, HttpServletRequest servletRequest) {
        try {
            if (bindingResult.hasErrors()) {
                return AidouResult.error(bindingResult.getFieldError().getDefaultMessage());
            }
            System.out.println("登录参数" + GsonUtil.gsonString(wechatAuthInfo));
            return wechatAuthService.login(wechatAuthInfo);
        } catch (WechatTokenNotFoundException e) {
            return AidouResult.error("获取用户token失败");
        } catch (WechatAesDesryptException w) {
            return AidouResult.error(w.getMessage());
        }

    }


    /**
     * 解密并且获取用户手机号码
     *
     * @return
     * @throws Exception 
     */
    @PostMapping(value = "deciphering")
    @ApiOperation(value = "解密", notes = "解密")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code", required = true),
            @ApiImplicitParam(name = "encrypdata", value = "主体加密数据", required = true),
            @ApiImplicitParam(name = "ivdata", value = "偏移量", required = true)
    }
    )
    public @ResponseBody AidouResult deciphering(@RequestBody WxPhoneRequest wxPhoneRequest, HttpServletRequest servletRequest) {
        try {
            String source = servletRequest.getHeader("source");
            String deciphering = wechatAuthService.deciphering(wxPhoneRequest.getEncrypdata(), wxPhoneRequest.getIvdata(), wxPhoneRequest.getCode(), source);
            return AidouResult.success(deciphering);
        } catch (Exception e) {
            e.printStackTrace();
            return AidouResult.error("解密失败");
        }
    }


    @PostMapping(value = "createdQRCode")
    @ApiOperation(value = "分享二维码生成", notes = "分享二维码生成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scene", value = "最大32个可见字符", required = true),
            @ApiImplicitParam(name = "page", value = "已经发布的小程序存在的页面 ", required = true),
            @ApiImplicitParam(name = "width", value = "二维码的宽度，单位 px，最小 280px，最大 1280px  默认430", required = false),
    }
    )
    public @ResponseBody AidouResult createdQRCode(@RequestBody QrCodeRequest qrCodeRequest) {
        try {
            return weChatMessagePushService.createdQRCode(qrCodeRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return AidouResult.error("解密失败");
        }
    }


}
