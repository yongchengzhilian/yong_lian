package com.aidou.api.util;

import com.aidou.api.common.cache.RedisDao;
import com.aidou.util.tool.GsonUtil;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by yingjiafeng on 2019/5/14.
 */
public class AppletFromIdUtil {
    static final String APPLET_MEMBER_FORM_ID_KEY = "WECHAT_FORM_ID:";

//    public   static void saveOrupdateFormId(Long  uid, String formId,RedisDao redisDao) {
//        String rediaKey=APPLET_MEMBER_FORM_ID_KEY+uid;
//        String value = redisDao.getValue(rediaKey);
//        long endTime=  System.currentTimeMillis()+604800000;
//        Map<String,String>     fromIdHashMap;
//        if (StringUtils.isEmpty(value)){
//            fromIdHashMap=new LinkedHashMap<>();
//            fromIdHashMap.put(formId,endTime+"");
//        }else {
//            fromIdHashMap= GsonUtil.gsonToMaps(value);
//            //获取最早要过期的
//            if (fromIdHashMap.size()>=5){
//                Iterator<Map.Entry<String, String>> it = fromIdHashMap.entrySet().iterator();
//                while(it.hasNext()){
//                    Map.Entry<String, String> entry = it.next();
//                    if (Long.valueOf(entry.getValue())>System.currentTimeMillis()){
//                        it.remove();
//                        break;
//                    }else {
//                        it.remove();
//                    }
//                }
//            }
//        }
//        fromIdHashMap.put(formId,endTime+"");
//        redisDao.setKeyByMillisseconds(rediaKey,GsonUtil.gsonString(fromIdHashMap),604800000);
//    }

//    public static  String getMemberFormId(Long uid, RedisDao redisDao) {
//        String rediaKey=APPLET_MEMBER_FORM_ID_KEY+uid;
//        String value = redisDao.getValue(rediaKey);
//        if (StringUtils.isEmpty(value)){
//            return null;
//        }
//        Map<String, String> stringObjectMap = GsonUtil.gsonToMaps(value);
//        //获取最早要过期的
//        Iterator<Map.Entry<String, String>> it = stringObjectMap.entrySet().iterator();
//        String fromId=null;
//        while(it.hasNext()){
//            Map.Entry<String, String> entry = it.next();
//            Long endTime=Long.valueOf(entry.getValue());
//            if (endTime>System.currentTimeMillis()){
//                it.remove();
//                fromId= entry.getKey();
//                break;
//            }else {
//                it.remove();
//            }
//        }
//        if (stringObjectMap.isEmpty()){
//            redisDao.delete(rediaKey);
//            return fromId;
//        }
//        redisDao.setKeyByMillisseconds(rediaKey,GsonUtil.gsonString(stringObjectMap),redisDao.getExpire(rediaKey));
//        return fromId;
//    }


}
