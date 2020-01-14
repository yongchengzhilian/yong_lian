package com.aidou.api.filter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yingjiafeng on 2019/4/29.
 */
public class FilterUrlUtil {

    /**
     * 保存不需要被拦截的路径
     */
    private static Set<String> urlList = new HashSet<>();

    /**
     * 需要判断用户状态的URL
     */
    private static Set<String> userStatusUrl = new HashSet<>();

    static {
        urlList.add("ocridcard");
        urlList.add("icp");
        urlList.add("wx");
        urlList.add("upload");
        urlList.add("pay") ;
        urlList.add("friend");
        urlList.add("wechat");
        urlList.add("/html");
        urlList.add("wxPay");
        urlList.add("jsPay");
        urlList.add("refreshToken");
        urlList.add("check");
        //swagger
        urlList.add("favicon");
        urlList.add("actuator");
        urlList.add("swagger");
        urlList.add("v2/api-docs");
        urlList.add("docApi");
        urlList.add("static");
        userStatusUrl.add("send/line");
    }

    /**
     * 不需要过滤的URL
     *
     * @param url
     * @return
     */
    public static boolean ifFilter(String url) {
        for (String urlIndex : urlList) {
            if (url.indexOf(urlIndex) > 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 用户过滤的URL
     *
     * @param url
     * @return
     */
    public static boolean ifUserStatusFilter(String url) {
        for (String urlIndex : userStatusUrl) {
            if (url.indexOf(urlIndex) > 0) {
                return false;
            }
        }
        return true;

    }


}
