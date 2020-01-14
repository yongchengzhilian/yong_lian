package com.aidou.api.vo.request.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/9
 * Description:
 * @author 1
 */

@Data
public class AccountLogoutRequest {

    @ApiModelProperty(value="账户状态(1：正常 5:注销 6:隐藏)",name="accountStatus")
    private Integer  accountStatus;


    @ApiModelProperty(value="注销原因",name="logoutCode")
    private Integer  logoutCode;

    @ApiModelProperty(value="备注",name="remarks")
    private String   remarks;
}
