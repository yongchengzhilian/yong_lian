package com.aidou.api.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class IdCardRequest {

    @ApiModelProperty(value = "图片地址",name = "imgUrl",required = true)
    @NotBlank(message = "图片地址不能为空")
    private String  imgUrl;

    @ApiModelProperty(value = "图片类型 back 背面  front  正面",name = "imgUrl",required = true)
    @NotBlank(message = "图片地址不能为空")
    private String   type;
}
