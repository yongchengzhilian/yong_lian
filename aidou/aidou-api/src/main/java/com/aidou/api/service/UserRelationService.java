package com.aidou.api.service;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/14
 * Description:
 * @author 1
 */
public interface UserRelationService {

    /**
     * 分享关系
     * @param sud  分享用户
     * @param uid  新用户
     */
      void  addRelation(Long sud,Long uid);


    /**
     * 分享用户数量
     * @param uid
     * @return
     */
      Integer  shareUserCount(Long uid);

    /**
     * 获取分享获得的红线数量
     * @param size
     * @return
     */
    Integer findShareRedCount(Integer size);

    /**
     * 获取因注册获取的红线数量
     * @return
     */
    Integer findShareRegisteredRedCount();
}
