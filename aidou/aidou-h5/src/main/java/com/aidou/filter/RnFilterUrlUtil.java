package com.aidou.filter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yingjiafeng on 2019/4/29.
 */
public class RnFilterUrlUtil {

    /**
     * 保存不需要被拦截的路径
     */
    private static Set<String> urlList=new HashSet<>();
    static {
        urlList.add("login");
        urlList.add("sms");
        //swagger
        urlList.add("favicon");
        urlList.add("actuator");
        urlList.add("swagger");
        urlList.add("v2/api-docs");
        urlList.add("docApi");
    }

    public static boolean  ifFilter(String url){
        for (String urlIndex:urlList) {
            if (url.indexOf(urlIndex)>0){
                return false;
            }
        }
        return true;
    }


}
