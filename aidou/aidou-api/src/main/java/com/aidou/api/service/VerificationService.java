package com.aidou.api.service;

import com.aidou.api.vo.request.IdCardRequest;
import com.aidou.api.vo.request.IdCardVerRequest;
import com.aidou.api.vo.request.UserInfoRequest;
import com.aidou.api.vo.respone.IdCardAppRespone;
import com.aidou.util.exception.BizException;

/**
 * Created by yingjiafeng on 2019/6/2.
 */
public interface VerificationService {
    /**
     * 身份证认证
     */
    String idCardVerified(IdCardVerRequest idCardVerRequest) throws BizException;

    /**
     * 用户资料提交
     *
     * @param userInfoRequest
     */
    void updateUserInfo(UserInfoRequest userInfoRequest) throws BizException;


    /**
     * 学历认证直接切入学信网获取学历信息
     *
     * @param verification
     * @return
     * @throws BizException
     */
    String schoolVerified(String verification) throws BizException;

    /**
     * 身份证识别
     *
     * @param idCardRequest
     */
    IdCardAppRespone idCardOrc(IdCardRequest idCardRequest) throws BizException;
}
