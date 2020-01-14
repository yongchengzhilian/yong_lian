package com.aidou.api.vo.wechat;

import lombok.Data;

@Data
public class WechatCustomerRespone {

    /**
     * ToUserName : gh_a1562eaf0139
     * FromUserName : opssg5eP3ILExd-8az9wj4lXFNek
     * CreateTime : 1576058910
     * MsgType : text
     * Content : 兔兔
     * MsgId : 22563737914747100
     */

    private String ToUserName;
    private String FromUserName;
    private int CreateTime;
    private String MsgType;
    private String Content;
    private long MsgId;

}
