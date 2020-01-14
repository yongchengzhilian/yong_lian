package com.aidou.api;

import org.apache.commons.lang.StringUtils;

/**
 * Created by yingjiafeng on 2019/6/4.
 */
public class Result {
    private static final long serialVersionUID = 1L;
    private int resultCode;
    private String message;
    private Object datas;

    public Result() {
    }

    public Result(int resultCode, String message, Object datas) {
        this.resultCode = resultCode;
        this.message = message;
        this.datas = datas;
    }

    public static Result success(Object object, String message) {
        return ok(ResultCode.SUCCESS.getCode(), message, object);
    }

    public static Result success(String message) {
        return ok(ResultCode.SUCCESS.getCode(), message, (Object)null);
    }

    public static Result success(Object object) {
        return ok(ResultCode.SUCCESS.getCode(), "", object);
    }

    public static Result success() {
        return ok(ResultCode.SUCCESS.getCode(), "", (Object)null);
    }

    public static Result error500() {
        return error(ResultCode.SERVICE_ERROR.getCode(), "");
    }

    public static Result error404() {
        return error(ResultCode.NOT_FOUNT.getCode(), "");
    }

    public static Result ok(int resultCode, String message, Object datas) {
        String msg = message;
        if (StringUtils.isBlank(message)) {
            msg = "ok";
        }

        Result r = new Result();
        r.setResultCode(resultCode);
        r.setMessage(msg);
        r.setDatas(datas);
        return r;
    }

    public static Result error(int resultCode, String message) {
        String msg = message;
        if (StringUtils.isBlank(message)) {
            msg = "error";
        }

        Result r = new Result();
        r.setResultCode(resultCode);
        r.setMessage(msg);
        return r;
    }

    public String toString() {
        return "Result{resultCode=" + this.resultCode + ", message='" + this.message + '\'' + ", datas=" + this.datas + '}';
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDatas() {
        return this.datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }


}
