package com.aidou.util.entity.wechat;

import lombok.Data;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/11 14:18
 */
@Data
public class WeChatMessageModel {
    //活动名称
    private String activityTitle;
    //活动时间
    private String activityTime;
    //活动地址
    private String activityAddress;
    private Long uid;
    private String page;
    private String result;
    private String remarks;
}
