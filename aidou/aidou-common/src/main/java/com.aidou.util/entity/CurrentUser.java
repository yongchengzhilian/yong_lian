package com.aidou.util.entity;


import lombok.Data;


/**
 * Created by Guoyi on 17/4/12.
 */
@Data
public class CurrentUser {

    private Long id;

    private Integer status;

    private String wechat;

    private Integer sex;
    private String mobile;

    private String nikeName;

    private boolean realName;


    private static ThreadLocal<CurrentUser> threadLocal = new ThreadLocal<CurrentUser>();


    public static void set(CurrentUser user) {
        threadLocal.set(user);
    }

    public static CurrentUser get() {
        return threadLocal.get();
    }


}
