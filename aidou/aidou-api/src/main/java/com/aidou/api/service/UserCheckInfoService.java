package com.aidou.api.service;

import com.aidou.api.vo.respone.SchoolCheckRespone;
import com.aidou.api.vo.respone.UserDateCheckRespone;
import com.aidou.util.exception.BizException;

import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/29 9:39
 */
public interface UserCheckInfoService {


    /**
     * 获取所有需审核用户列表
     * -1:审核拒绝  1:通过 2：待审核  0：所有
     * @param page  页码
     * @param rows  行数
     * @param status  -1:审核拒绝  1:通过 2：待审核  0：所有
     * @return
     * @throws BizException
     */
    List<UserDateCheckRespone>    findAllCheckInfoList(int page, int rows,int status)throws BizException;

    /**
     * 获取所有学校审核列表
     * @param page  页码
     * @param rows  行数
     * @param status   -1：未认证  1：认证中   2： 已认证
     * @return
     * @throws BizException
     */
    List<SchoolCheckRespone> findAllSchoolList(Integer page, int rows, Integer status)throws BizException;
}
