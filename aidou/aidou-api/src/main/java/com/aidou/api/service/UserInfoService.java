package com.aidou.api.service;

import com.aidou.api.vo.respone.JsPayUserInfoRespone;
import com.aidou.dao.entity.TbUser;
import com.aidou.dao.entity.TbUserContent;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.enums.UserStatusEnum;
import com.aidou.util.exception.BizException;

import java.util.List;

/**
 * Created by yingjiafeng on 2019/5/21.
 */
public interface UserInfoService {





    /**
     * 根据UID获取用户自我介绍  折偶需求
     * @param uid
     * @return
     */
    TbUserContent   findUserContentByUid(Long uid) throws BizException;

    /**
     * 根据用户ID 集合获取用户列表
     * @param ids
     * @return
     * @throws BizException
     */
    List<TbUser>  findUserListByIds(List<Long>  ids) throws BizException;


    /**
     * 修改用户状态
     * @param id  用户ID
     * @param userStatusEnum 状态
     * @return
     */
    int  updateUserStatus(Long id, UserStatusEnum userStatusEnum) throws BizException;



    /**
     * 添加学信网code
     * @param code
     * @throws BizException
     */
    void   addSchoolCode(String  code) throws BizException;


    /**
     * 更新用户
     * @param tbUser
     * @return
     * @throws BizException
     */
    int  updateUser(TbUser tbUser) throws BizException;

    TbUser  findUserById(Long uid);


    /**
     * 获取用户的轻量级信息（不安全）
     * @param userId
     * @return
     */
    JsPayUserInfoRespone selectPayUserInfo(Long userId);
}
