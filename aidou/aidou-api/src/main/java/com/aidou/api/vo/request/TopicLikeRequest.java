package com.aidou.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(value = "TopicLikeRequest", description = "点赞")
@Data
public class TopicLikeRequest {

    @ApiModelProperty(value = "话题ID /如果是评论点赞则 评论ID")
    @NotNull(message="话题ID不能为空！")
    private Long  topicId;

    @ApiModelProperty(value = "状态")
    @NotNull(message=" -1 关闭  1 点赞")
    private Integer  likeStatus;

}
