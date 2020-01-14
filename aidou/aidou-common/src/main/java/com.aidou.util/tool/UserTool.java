package com.aidou.util.tool;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class UserTool {

    private static String  IMAGE_SERVER_URL="http://cdn.aidou.online/";

    /**
     * 头像转换
     * @param topImage
     * @return
     */
    public static String  topImageCover(String  topImage,Integer sex){
        if (StringUtils.isEmpty(topImage)){
            return sex == 1 ? "http://cdn.aidou.online/boy.jpg" : "http://cdn.aidou.online/girl.jpg";
        }
        List<String> strings = GsonUtil.gsonToList(topImage, String.class);
        if (strings.get(0).startsWith("http")){
            return strings.get(0);
        }else {
            return IMAGE_SERVER_URL+strings.get(0);
        }

    }


}
