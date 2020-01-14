package com.aidou.service;

import com.aidou.util.entity.AidouResult;
import com.aidou.vo.rm.UserInfoEditDto;

public interface MatchmakerService {



    AidouResult AddUser(UserInfoEditDto userInfoEditDto) throws  Exception;

    AidouResult findUserList();

    /**
     * 停止用户
     * @param uid
     * @param type
     * @return
     */
    AidouResult stopUser(Long uid, Integer type);

    /**
     * 获取用户详情
     * @param id
     * @return
     */
    AidouResult findUserDescriptById(Long id)  throws NullPointerException;
}
