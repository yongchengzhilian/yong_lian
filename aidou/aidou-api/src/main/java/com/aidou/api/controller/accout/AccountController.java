package com.aidou.api.controller.accout;

import com.aidou.api.service.AccountService;
import com.aidou.api.vo.request.account.AccountLogoutRequest;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.StatusCode;
import com.aidou.util.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/9
 * Description:
 */

@Slf4j
@Api(tags = "账户设置")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @ApiOperation(value = "账户状态设置", notes = "账户状态设置")
    @ApiResponses({
            @ApiResponse(code = StatusCode.OK, message = "账户状态设置", response = AidouResult.class)
    })
    @ApiResponse(code = StatusCode.OK, message = "账户状态设置", response = AidouResult.class)
    @PostMapping(value = "/accountStatus")
    public AidouResult getRecommendUserList(@RequestBody AccountLogoutRequest accountLogoutRequest) {
        try {
            accountService.logout(accountLogoutRequest);
           return AidouResult.success();
        }
        catch (BizException e){
            e.printStackTrace();
            return AidouResult.error(e.getLocalizedMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return AidouResult.error("获取数据失败");
        }
    }


}
