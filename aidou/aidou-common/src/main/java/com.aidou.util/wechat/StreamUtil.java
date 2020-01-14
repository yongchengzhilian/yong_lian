package com.aidou.util.wechat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil {

    public static String  inputStreamToString(InputStream inputStream){
        try {
            BufferedReader streamReader = new BufferedReader( new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null){
                responseStrBuilder.append(inputStr);
            }
            return responseStrBuilder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

}
