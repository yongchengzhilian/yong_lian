package com.aidou.api.service;

import com.aidou.api.vo.request.UserRecommendListRequest;
import com.aidou.api.vo.user.ReommendUserVo;
import com.aidou.api.vo.user.UserDetailsVo;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.exception.BizException;

import java.util.List;

/**
 * 用户推荐
 * Created by yingjiafeng on 2019/5/20.
 */
public interface FriendRecommendService {

    AidouResult searchlist(UserRecommendListRequest userRecommendListRequest)throws BizException;

    /**
     * 获取用户详情
     * @param uid
     * @return
     */
    UserDetailsVo findUserDescriptByUid(Long uid) throws BizException;



}
