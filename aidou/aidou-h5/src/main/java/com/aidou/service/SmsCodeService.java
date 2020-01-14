package com.aidou.service;


import com.aidou.enums.SmsCodeType;
import com.aidou.util.entity.AidouResult;

public interface SmsCodeService {

    AidouResult makeAndSend(String mobile, SmsCodeType bizType, String ipAddress) ;


    AidouResult validateLoginCode(String ipAddress, String mobile, String smsCode);


}
