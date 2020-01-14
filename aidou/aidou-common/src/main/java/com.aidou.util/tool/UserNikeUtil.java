package com.aidou.util.tool;

import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/8/20 17:10
 */
public class UserNikeUtil {


    /**
     * 根据性别生成用户昵称 允许重复
     * @param sex
     * @return
     */
    public static String   getUserNikeName(Integer sex){
        int i = new Random().nextInt(999999);
        return  sex==1?"G"+i:"M"+i;

    }


}
