package com.aidou.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@ApiModel(value = "TopicVoteRequest", description = "话题投票")
@Data
public class TopicVoteRequest {

    @ApiModelProperty(value = "话题ID")
    @NotNull(message="话题ID不能为空！")
    private   Long  topicId;

    @ApiModelProperty(value = "投票ID")
    private   Long  selectId;


    @ApiModelProperty(value = "投票类型")
    @NotNull(message="投票类型不能为空！ 1:投票  2：取消")
    private   Integer  selectType;

}
