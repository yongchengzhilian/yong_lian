package com.aidou.api.vo.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "TopicRequest", description = "话题")
@Data
public class TopicRequest {

    @ApiModelProperty(value = "内容")
    @NotNull(message="内容不能为空！")
    private String content;

    @ApiModelProperty(value = "话题选择列表")
    private List<String> selectTopisList;

    @ApiModelProperty(value = "图片")
    private List<String>  imageList;

    @ApiModelProperty(value = "话题类型  1:内容  2:投票")
    private Integer type = 1;
}
