package com.aidou.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(value = "TopicStatusRequest", description = "话题状态修改")
@Data
public class TopicStatusRequest {

    @ApiModelProperty(value = "话题ID")
    @NotNull(message="话题ID不能为空！")
    private Long  topicId;

    @ApiModelProperty(value = "状态")
    @NotNull(message="状态不能为空！ -1 关闭  1 打开")
    private Integer  status;

}
