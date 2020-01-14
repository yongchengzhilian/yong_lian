package com.aidou.api.service;

import com.aidou.api.vo.request.ReportUserRequest;
import com.aidou.api.vo.request.SchoolCheckRequest;
import com.aidou.api.vo.request.UserCheckRequest;
import com.aidou.api.vo.request.UserReportRequest;
import com.aidou.api.vo.respone.ReportUserItem;
import com.aidou.util.exception.BizException;

import java.util.List;

/**
 * Created by yingjiafeng on 2019/6/3.
 */
public interface ReportService {



    /**
     * 举报用户
     * @param reportUserRequest
     * @throws BizException
     */
    void reportUser(ReportUserRequest reportUserRequest) throws BizException;


    /**
     * 举报审核
     * @throws BizException
     */
    void  reportCheck(UserReportRequest  reportRequest) throws  BizException;

    /**
     * 审核用户资料
     * @param checkRequest
     * @throws BizException
     */
    Integer checkUserInfo(UserCheckRequest checkRequest)throws  BizException;

 /**
  * 学历审核 返回2 审核成功
  * @param checkRequest
  * @return
  */
 int schoolCheck(SchoolCheckRequest checkRequest)throws BizException;

 /**
  * 举报用户列表
  * @param page  页码
  * @param rows  行数
  * @param status  状态  1:审核  2:未审核
  * @return  被举报用户列表
  * @throws BizException
  */
 List<ReportUserItem>   findReportUserList(int page, int rows,int status) throws BizException;


 /**
  * 获取被举报次数
  * @param uid 用户id
  * @return
  * @throws BizException
  */
 Integer   findReportCountByUid(Long uid)throws BizException;

}
