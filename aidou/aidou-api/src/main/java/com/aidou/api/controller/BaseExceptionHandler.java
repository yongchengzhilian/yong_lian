package com.aidou.api.controller;
import com.aidou.api.exception.UserLockException;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.StatusCode;
import com.aidou.util.exception.ExpiredTokenException;
import com.aidou.util.exception.InvalidTokenException;
import com.aidou.util.tool.ExceptionUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {
	
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public AidouResult error(Exception e){
        System.out.println("进入错误日志里");
        e.printStackTrace();
        String exceptionMsg = ExceptionUtil.getExceptionAllinformation_01(e);
        System.out.println(exceptionMsg);
        return AidouResult.error(exceptionMsg);
    }
}
