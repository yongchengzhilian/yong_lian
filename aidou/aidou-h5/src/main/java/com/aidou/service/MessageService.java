package com.aidou.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

/**
 * Created by Guoyi on 17/4/18.
 */
public interface MessageService {

    SendSmsResponse sendSms(String mobile, String content) throws ClientException;
}
