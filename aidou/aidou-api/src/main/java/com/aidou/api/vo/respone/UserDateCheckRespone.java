package com.aidou.api.vo.respone;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/29 9:41
 */
@Data
public class UserDateCheckRespone {

    private String uid;

    private String wechat;

    private String mobile;

    private String income;

    private String education;

    private String houseCar;

    private String marriage;

    private String town;

    private Integer weight;

    private Integer height;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date created;

    private String work;

    private String school;

    private Integer status;

    private String remarks;

    private String content;

    private String interest;

    private String favoriteTa;

    private List<String> photoList;

    private String topImageItem;

}
