package com.aidou.api.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@ApiModel(value = "TopicMessageRespone", description = "我的消息")
@Data
public class TopicMessageRespone {

    @ApiModelProperty(value = "头像")
    private String  faceImg;

    @ApiModelProperty(value = "话题ID")
    private String  topicId;

    @ApiModelProperty(value = "昵称")
    private String nikeName;

    @ApiModelProperty(value = "回复ID")
    private String   commidId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;

    @ApiModelProperty(value = "话题内容")
    private String content;

    @ApiModelProperty(value = "别人给我的评论")
    private String toContemt;


}
