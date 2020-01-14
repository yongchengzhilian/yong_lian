package com.aidou.api.controller.system;


import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.StatusCode;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "错误回传(非业务)")
@RestController
@RequestMapping("/error")
public class ErrorController {



    @PostMapping(value = "/msg/{code}")
    public AidouResult matchmakerInfo(@PathVariable Integer code){
        System.out.println("code"+code);
        switch (code){
            case StatusCode.LOGIN:
                return AidouResult.success( StatusCode.LOGIN, "请重新登陆");
            case StatusCode.USER_STOP_LOGIN:
                return AidouResult.success(StatusCode.USER_STOP_LOGIN, "用户被锁定");
                default:
                    return  AidouResult.error("未知Code"+code);
        }

    }
}
