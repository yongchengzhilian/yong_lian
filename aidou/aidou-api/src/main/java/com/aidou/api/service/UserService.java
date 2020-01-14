package com.aidou.api.service;

import com.aidou.api.vo.UserToken;
import com.aidou.api.vo.request.UserInfoRequest;
import com.aidou.api.vo.request.UserSmallInfoRequest;
import com.aidou.api.vo.respone.UserCenterInfoRespone;
import com.aidou.dao.entity.TbUser;
import com.aidou.util.exception.BizException;

import java.util.List;

/**
 * 用户行为相关
 * Created by yingjiafeng on 2019/5/24.
 */
public interface UserService {

    /**
     * 发送红线
     * @param toUid 目标用户
     * @param content
     * @return
     */
    void  sendRedLine(Long toUid, String content) throws BizException;


    /**
     * 获取当前用户资料
     * @return
     * @throws BizException
     */
    UserInfoRequest findCurrentEditUserInfo() throws BizException ;



    /**
     * 个人中心
     * @return
     * @throws BizException
     */
    UserCenterInfoRespone findUserCenterInfo() throws BizException;

    /**
     * 获取当前用户状态信息
     * @return
     */
    UserToken findCurrentUserStatus();

    /**
     * 获取系统需要赠送的用户
     * @return
     */
    List<Long> findSystemGiftUser();

    TbUser  findUserById(Long uid);

    List<TbUser>  findUserByIdLiST(List<Long>  idList);


    /**
     * 牵线同意
     * @param id  列表ID
     */
    void agreeLike(Long id)throws BizException;

    /**
     * 牵线拒绝
     * @param id  列表ID
     * @throws BizException
     */
    void refuseLike(Long id)throws BizException;


    /**
     * 申请拒绝
     * @param id
     * @throws BizException
     */
    void applicationRefuse(Long id) throws BizException;


    /**
     * 修改头像 昵称
     * @param userSmallInfoRequest
     */
    void updatedUserSmallInfo(UserSmallInfoRequest userSmallInfoRequest);
}
