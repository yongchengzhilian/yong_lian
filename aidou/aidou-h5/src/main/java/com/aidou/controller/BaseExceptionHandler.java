package com.aidou.controller;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.StatusCode;
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
        e.printStackTrace();        
        return new AidouResult(false, StatusCode.ERROR, "执行出错");
    }
}
