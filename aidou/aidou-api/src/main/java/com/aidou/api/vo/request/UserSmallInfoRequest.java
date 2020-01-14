package com.aidou.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@ApiModel(value = "UserSmallInfoRequest", description = "用户个人资料")
@Data
public class UserSmallInfoRequest {

    @ApiModelProperty(value = "昵称")
    @NotNull(message = "昵称不能为空！")
    private String  nikeName;

    @ApiModelProperty(value = "头像")
    @NotNull(message = "头像不能为空！")
    private String  faceUrl;


}
