package com.aidou.api.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderCreateRequest {

    @ApiModelProperty(value="商品ID",name="itemId")
    @NotNull(message = "商品Id不能为空")
    private Long  itemId;

    @ApiModelProperty(value="用户ID 优先获取用户ID 如果不存在则根据token获取",name="userId")
    private Long  userId;

    @ApiModelProperty(value="支付类型",name="payType   app: 小程序支付   H5：H5支付")
    private String  payType;

}
