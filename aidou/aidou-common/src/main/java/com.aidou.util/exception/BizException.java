package com.aidou.util.exception;

/**
 * Created by Guoyi on 17/4/16.
 */
public class BizException extends RuntimeException {


    public BizException(){

    }
    public BizException(String message){
        super(message);
    }

}
