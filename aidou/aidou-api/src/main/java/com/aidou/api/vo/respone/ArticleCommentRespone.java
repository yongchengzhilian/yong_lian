package com.aidou.api.vo.respone;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value = "ArticleCommentRespone", description = "评论列表")
@Data
public class ArticleCommentRespone implements Serializable {


    @ApiModelProperty(value = "话题ID", name = "topicId")
    private String  topicId;

    @ApiModelProperty(value = "评论ID", name = "id")
    private String id;
    //评论用户
    @ApiModelProperty(value = "评论用户ID", name = "uid")
    private String uid;
    //评论时间
    @ApiModelProperty(value = "评论时间", name = "created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;
    //用户头像
    @ApiModelProperty(value = "用户头像", name = "faceImg")
    private String faceImg;

    @ApiModelProperty(value = "用户昵称", name = "nikeName")
    private String nikeName;

    @ApiModelProperty(value = "点赞数", name = "thumbupCount")
    private Long thumbupCount = 0L;

    @ApiModelProperty(value = "评论数", name = "commentCount")
    private Long commentCount = 0L;

    @ApiModelProperty(value = "内容", name = "content")
    private String content;
    //=
    @ApiModelProperty(value = "评论类型", name = "0 代表对话题的评论   其他代表作者对用户的评论")
    private String parentId;

    //回复
    private List<ArticleAutoCommentRespone> authCommentList = new ArrayList<>();


}



