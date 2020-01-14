package com.aidou.api.util;

/**
 * Created by yingjiafeng on 2019/5/21.
 */
public class AidouImageUtil {


    private static String  IMAGE_SERVER_URL="http://cdn.aidou.online/";


    public static String   getHttpUrl(String image){

        if (image.startsWith("http")){
            return image;
        }else {
            return IMAGE_SERVER_URL+image;
        }



    }

}
