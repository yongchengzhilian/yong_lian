package com.aidou.api.vo.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "TopicListRequest", description = "话题列表")
@Data
public class TopicListRequest {

    @ApiModelProperty(value = "1:最新  2 :最热")
    private Integer  type=1;


}
