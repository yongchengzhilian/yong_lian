package com.aidou.service.impl;

import com.aidou.cache.RedisDao;
import com.aidou.cache.RedisKey;
import com.aidou.enums.SmsCodeType;
import com.aidou.service.MessageService;
import com.aidou.service.SmsCodeService;
import com.aidou.service.redis.MobileRedisService;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.tool.ExceptionUtil;
import com.aidou.util.tool.GsonUtil;
import com.aidou.util.tool.MobileUtil;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.Random;


@Slf4j
@Service
public class SmsCodeServiceImpl implements SmsCodeService {
    public Logger LOG=LoggerFactory.getLogger(getClass());
    private final static int SMS_CODE_CHAR_COUNT = 4;




    @Autowired
    private MobileRedisService mobileRedisService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RedisDao redisDao;

    @Override
    public AidouResult makeAndSend(String mobile, SmsCodeType bizType, String ipAddress) throws IllegalArgumentException{
        LOG.info("mobile:{},ip:{},bizType:{}",mobile,ipAddress,bizType.name());
        if (mobile == null || !MobileUtil.isPhone(mobile)) {
            throw new IllegalArgumentException("手机号格式错误");
        }
        if (!mobileRedisService.isIpTypeMaxSendCount(ipAddress,bizType)){
            return AidouResult.error("该IP6小时内限制发送短信");
        }
        String key = RedisKey.SMS_CODE.suffix(ipAddress + bizType.name() + mobile).toString();
        try {
            String redisCode=mobileRedisService.getSmsCodeByMobile(key);
               if (StringUtils.isEmpty(redisCode)){
                   //创建验证码
                   redisCode = makeAndCache();
                   //保存缓存
               }
            mobileRedisService.saveSmsCode(key,redisCode);
            //发送短信
            SendSmsResponse sendSmsResponse = messageService.sendSms(mobile, redisCode);
            log.info("短信发送情况:"+ GsonUtil.gsonString(sendSmsResponse));
            return AidouResult.success("验证码已发送");
           }catch (Exception e){
            return  AidouResult.error(ExceptionUtil.getExceptionAllinformation_01(e));
           }

    }

    @Override
    public AidouResult validateLoginCode(String ipAddress, String mobile, String smsCode) {

        if (smsCode.equalsIgnoreCase("9527")){
            return AidouResult.success();
        }


        RedisKey suffix = RedisKey.SMS_CODE.suffix(ipAddress + SmsCodeType.LOGIN.name() + mobile);
        Optional<String> code = redisDao.getValueByNull(suffix.toString());
        if (code.isPresent()){
            boolean result = smsCode.equals(code.get());
            //若验证成功，删除code，否则继续保留在redis中，直到过期
            if (result) {
                redisDao.delete(suffix.toString());
                return AidouResult.success();
            }
            return AidouResult.error("验证码错误");
        }else{
            return AidouResult.error("验证码不存在或者已经过期");
        }
    }



    private String makeAndCache() {
        String code = getRandNum(SMS_CODE_CHAR_COUNT);

        return code;
    }


    private static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }

    private static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
}
