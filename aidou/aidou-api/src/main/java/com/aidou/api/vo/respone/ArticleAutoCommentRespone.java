package com.aidou.api.vo.respone;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ArticleAutoCommentRespone implements Serializable {

    @ApiModelProperty(value = "id", name = "id")
    private String  id;

    @ApiModelProperty(value = "回复用户ID", name = "uid")
    private String uid;

    @ApiModelProperty(value = "头像")
    private String  faceImg;

    @ApiModelProperty(value = "昵称")
    private String nikeName;

    private String  parentId;

    //评论时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date  created;
    //用户头像
    private String  content;

}



