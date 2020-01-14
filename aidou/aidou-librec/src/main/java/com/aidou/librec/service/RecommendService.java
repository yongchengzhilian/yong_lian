package com.aidou.librec.service;

import com.aidou.librec.vo.UserSet;
import com.aidou.librec.vo.UserVo;
import com.aidou.util.exception.BizException;

import java.util.List;

/**
 * Created by yingjiafeng on 2019/5/21.
 */
public interface RecommendService {

    /**
     * 发现用户最适合的10个人
     * @param myVo
     * @param userVoList
     * @return
     */
    List<Long>   findMostFriendByUserMap(UserVo myVo, List<UserVo> userVoList) throws BizException;





}
