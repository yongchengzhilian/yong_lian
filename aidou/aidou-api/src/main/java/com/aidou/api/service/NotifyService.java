package com.aidou.api.service;

import com.aidou.api.vo.respone.NotifyItemRespone;
import com.aidou.dao.entity.TbFriendApplication;
import com.aidou.util.exception.BizException;

import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/25 9:12
 */
public interface NotifyService {

    /**
     * 获取通知列表
     * @return
     * @throws BizException
     */
    List<NotifyItemRespone>    findNotifyList() throws BizException;


    /**
     * 删除通知
     * @param mid  id
     * @return
     * @throws BizException
     */
    int  deleteNotigyById(Long mid)throws BizException;


    /**
     * 获取消息详情
     * @param mid
     * @return 详情页
     * @throws BizException
     */
    NotifyItemRespone   getNotifyByMid(Long mid)throws BizException;


    /**
     * 是否有新通知
     * @return
     * @throws BizException
     */
    int   searchIsNewNotifyBy() throws BizException;



    /**
     *  用户牵线通知
     * @param uid 用户ID
     * @param toUid 目标用户ID
     * @throws BizException
     */
    void  addUserFriendNotification( Long uid,Long toUid) throws BizException;


    /**
     * 牵线同意
     * @throws BizException
     */
    void  friendAgreeNotification(TbFriendApplication friendApplication)throws BizException;


    /**
     * 添加用户通知
     * @param id  用户ID
     * @param content 内容
     * @param title 标题
     * @throws BizException
     */
    void addUserNotification(Long uid, String content, String title) throws BizException;
}
