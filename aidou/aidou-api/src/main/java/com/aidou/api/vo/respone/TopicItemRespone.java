package com.aidou.api.vo.respone;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@ApiModel(value = "TopicItemRespone", description = "话题项")
@Data
public class TopicItemRespone {


    private String id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "图片")
    private List<String> imgList;

    @ApiModelProperty(value = "话题类型  1:文字话题  2:投票话题")
    private Integer type;

    @ApiModelProperty(value = "下拉列表")
    private List<TopicVoteRespone> selectItem;

    @ApiModelProperty(value = "浏览量")
    private Long visits = 0L;

    @ApiModelProperty(value = "点赞数")
    private Long thumbup = 0L;

    @ApiModelProperty(value = "评论数")
    private Long comment = 0L;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;

    @ApiModelProperty(value = "是否点赞")
    private Boolean isZan = false;

    @ApiModelProperty(value = "是否选择")
    private Boolean  isSelect=false;
}
