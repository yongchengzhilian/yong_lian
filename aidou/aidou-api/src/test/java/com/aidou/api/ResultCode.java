package com.aidou.api;

/**
 * Created by yingjiafeng on 2019/6/4.
 */
public enum ResultCode {
    SUCCESS(200, "获取成功"),
    NOT_FOUNT(404, "访问记录不存在"),
    SERVICE_ERROR(500, "服务器发生错误");

    private int code;
    private String msg;

    private ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
