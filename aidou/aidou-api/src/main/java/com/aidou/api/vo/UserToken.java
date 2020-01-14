package com.aidou.api.vo;

import lombok.Data;

/**
 * Created by yingjiafeng on 2019/6/2.
 */
@Data
public class UserToken {
    //普通用户状态
    /**
     * -1 ： 禁止  1：需资料完善  3：单身用户  4：牵线中
     */
    private Integer status;

    //是否实名
    private boolean isRealName=false;

    /**
     * 红线数量
     */
    private Integer  redCount;

    /**
     * 账户状态
     */
    private Integer accountStatus;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 牵线数量
     */
    private Integer  matchmakingCount;

    /**
     * 发现数量
     */
    private Integer  findCount;


}
