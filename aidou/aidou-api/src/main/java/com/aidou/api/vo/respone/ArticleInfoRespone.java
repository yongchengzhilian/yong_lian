package com.aidou.api.vo.respone;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleInfoRespone {

    private String id;
    private String userid;

    private String nikeName;

    private String  faceImg;

    private String title;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date created;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updated;

    private String istop;

    private Integer visits;

    private Integer thumbup;

    private Integer comment;

    private Integer state;

    private String content;



}
