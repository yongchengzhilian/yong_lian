package com.aidou.api.vo.request.wechat;

import lombok.Data;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/11
 * Description:
 * @author 1
 */
@Data
public class WechatPayRequest {

    private String  orderId;

    private Long  price;

    private String openId;



}
