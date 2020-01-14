package com.aidou.util.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回结果
 */
@Data
public class AidouResult implements Serializable {


    private boolean flag;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private  Long total;
    private Object data;// 返回数据

    public AidouResult(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public AidouResult(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public static AidouResult success(Object data) {
       return new AidouResult(true, StatusCode.OK,"操作成功",data);
    }

    public static AidouResult success() {
        return new AidouResult(true, StatusCode.OK,"操作成功");
    }

    public static AidouResult success(Integer code, String msg, Object data) {
        return new AidouResult(true, code,msg,data);
    }

    public static AidouResult success(Integer code, String msg) {
        return new AidouResult(true, code,msg);
    }

    public static AidouResult success(String msg, Object data) {
        return new AidouResult(true, StatusCode.OK,msg,data);
    }

    public static AidouResult error(String msg) {
        return new AidouResult(false, StatusCode.ERROR,msg);
    }



}


