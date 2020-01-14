package com.aidou.api.adapter;

import java.util.Date;
import java.util.HashMap;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/8
 * Description:
 */
public class AppDateInitConfig {

    public static HashMap<Long, Date>   userLastTimeMap=new HashMap<>();


    /**
     * 设置用户最后登录
     */
    public static void putDate(Long  userId){
        userLastTimeMap.put(userId,new Date());
    }


}
