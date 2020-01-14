package com.aidou.api.service;

import com.aidou.api.vo.user.UserLikeVo;
import com.aidou.dao.entity.TbFriendApplication;
import com.aidou.util.exception.BizException;

import java.util.List;

/**
 *用户关系
 * Created by yingjiafeng on 2019/5/24.
 */
public interface FriendApplicationService {





    void  createdConfession(Long toid,String content) throws BizException;


    /**
     * 是否表白中
     * @param tagerUid
     * @return
     */
    boolean isConfession(Long tagerUid);

    /**
     * 获取互相喜欢的用户
     * @return
     */
    List<UserLikeVo> getLikeEachUserList() throws BizException;


    /**
     * 获取喜欢我的
     * @return
     */
    List<UserLikeVo> getLikeAppleList();


    /**
     * 更新列表状态
     * @param friendApplication
     * @return
     */
    int  updateUserLikeStatus(TbFriendApplication  friendApplication);



    /**
     * 我喜欢的 数量
     * @return
     * @throws BizException
     */
    Integer  findLikeCount() throws BizException;

    /**
     * 喜欢我的数量
     * @return
     * @throws BizException
     */
    Integer  findLikeMeCount() throws BizException;

    /**
     * 互相喜欢
     * @return
     * @throws BizException
     */
    Integer  findEachLikeCount() throws BizException;



    /**
     * 隐藏用户所有发送的好友申请
     * @param uid 用户ID
     * @throws BizException
     */
    void hideFriendApplicationByUid(Long uid) throws BizException;


    /**
     * 显示用户所发送的好友申请
     * @param uid
     * @throws BizException
     */
    void showFriendApplicationByUid(Long uid) throws BizException;

    /**
     * 红线回退
     * @param tbFriendApplications
     */
    void goBackToUser(List<TbFriendApplication> tbFriendApplications);
}
