package com.aidou.cache;

import java.text.MessageFormat;

public enum     RedisKey {
    SMS_CODE_IP("sms_code_ip@{0}"),
    SMS_CODE("sms_code@{0}"),
    USER_INFO_CODE("user_info_code@{0}");

    private String pattern;
    private String suffix;

    RedisKey(String pattern) {
        this.pattern = pattern;
    }

    public RedisKey suffix(String suffix){
        this.suffix = suffix;
        return this;
    }
    public String toString(){
       return MessageFormat.format(this.pattern,suffix);
    }
}
