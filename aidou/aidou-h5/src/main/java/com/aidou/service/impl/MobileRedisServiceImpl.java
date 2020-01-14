package com.aidou.service.impl;

import com.aidou.cache.RedisDao;
import com.aidou.cache.RedisKey;
import com.aidou.enums.SmsCodeType;
import com.aidou.service.redis.MobileRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by yingjiafeng on 2019/4/24.
 */
@Service
public class MobileRedisServiceImpl implements MobileRedisService {
    @Autowired
    private RedisDao redisDao;



    @Override
    public boolean isIpTypeMaxSendCount(String ipAddress, SmsCodeType bizType) {
        //判断一个IP24小时最多只能发送5条验证码
        String ipCount = RedisKey.SMS_CODE_IP.suffix(ipAddress + bizType.name()).toString();
        //判断该IP发送短信的5次数
        String count =redisDao.getValue(ipCount);
        Integer maxCount=1;
        if (!StringUtils.isEmpty(count)){
            maxCount=Integer.valueOf(count);
            maxCount++;
        }
        if (maxCount>=5){
            return false;
        }
        redisDao.setKey(ipCount,maxCount+"",300);
        return true;
    }

    @Override
    public String getSmsCodeByMobile(String key) {

        String value = redisDao.getValue(key);
        return value;
    }

    @Override
    public void saveSmsCode(String key, String code) {
        redisDao.setKey(key,code,10);
    }
}
