package com.aidou.api.service;

public interface WeChatContentCheckService {

    /**
     * 内容审核
     * @param content
     * @return
     */
    boolean     msgSecCheck(String  content);


}
