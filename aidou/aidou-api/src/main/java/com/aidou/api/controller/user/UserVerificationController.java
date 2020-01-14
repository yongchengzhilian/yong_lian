package com.aidou.api.controller.user;

import com.aidou.api.controller.BaseController;
import com.aidou.api.service.VerificationService;
import com.aidou.api.vo.request.IdCardRequest;
import com.aidou.api.vo.request.IdCardVerRequest;
import com.aidou.api.vo.request.UserInfoRequest;
import com.aidou.api.vo.respone.CardsBean;
import com.aidou.api.vo.respone.IdCardAppRespone;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.exception.BizException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yingjiafeng on 2019/6/2.
 */

@Slf4j
@Api(tags = "用户信息验证")
@RestController
@RequestMapping("/verification")
public class UserVerificationController extends BaseController {

    @Autowired
    private VerificationService verificationService;


    @ApiOperation(value = "身份证识别", notes = "身份证识别")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.SC_OK, message = "身份证识别", response = CardsBean.class)
    })
    @PostMapping(value = "/ocridcard")
    public AidouResult   idCardVerified(@RequestBody IdCardRequest idCardRequest){
        try {
            IdCardAppRespone cardsBean = verificationService.idCardOrc(idCardRequest);
            return AidouResult.success(cardsBean);
        }
        catch (BizException u){
            return AidouResult.error(u.getLocalizedMessage());
        }
    }




    @ApiOperation(value = "身份证认证", notes = "身份证认证")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.SC_OK, message = "身份证认证", response = AidouResult.class)
    })
    @PostMapping(value = "/idCard")
    public AidouResult   idCardVerified(@Valid @RequestBody IdCardVerRequest idCardVerRequest, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                return AidouResult.error(bindingResult.getFieldError().getDefaultMessage());
            }
            String s = verificationService.idCardVerified(idCardVerRequest);
            return s.equalsIgnoreCase("ok")?AidouResult.success():AidouResult.error(s);
        }
        catch (BizException u){
            return AidouResult.error(u.getLocalizedMessage());
        }
    }



    @ApiOperation(value = "学历认证", notes = "学历认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "verification", value = "在线验证码", required = true)
    }
    )
    @PostMapping(value = "/school/{verification}")
    public AidouResult   schoolVerified(@PathVariable String  verification){
        try {
            String   msg=   verificationService.schoolVerified(verification);
            return AidouResult.success(msg);
        }
        catch (BizException u){
            return AidouResult.error(u.getMessage());
        }
    }



    @ApiOperation(value = "用戶資料", notes = "用戶資料")
    @PostMapping(value = "/userInfo")
    public AidouResult   updateUserInfo(@Valid @RequestBody UserInfoRequest userInfoRequest, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                return AidouResult.error(bindingResult.getFieldError().getDefaultMessage());
            }
            verificationService.updateUserInfo(userInfoRequest);
            return  AidouResult.success() ;
        }
        catch (BizException u){
            return AidouResult.error(u.getMessage());
        }
    }


}
