package com.aidou.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/7
 * Description:
 */
@Data
@ApiModel(value = "PageExtAction", description = "参数对象")
public class PageExtAction {

    /**
     * 起始页数
     */
    @NotNull(message="page不能为空")
    @ApiModelProperty(value="起始页码",name="page",example="1")
    private Integer page = 1;

    /**
     * 显示的总页数
     */
    @NotNull(message="limit不能为空")
    @ApiModelProperty(value="最大页码",name="limit",example="10")
    private Integer limit = 10;

}
