package com.aidou.api.service;

import com.aidou.util.exception.BizException;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/8/4 13:16
 */
public interface UserCardService {


    /**
     * 获取身份证名称
     * @param uid   用户ID
     * @return
     * @throws BizException
     */
    String  findCadNameById(Long uid) throws BizException;

}
