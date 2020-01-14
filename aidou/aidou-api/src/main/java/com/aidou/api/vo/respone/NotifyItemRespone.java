package com.aidou.api.vo.respone;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/25 9:13
 */

@Data
public class NotifyItemRespone {


    private Boolean  isRead;
    private String mid;
    private String title;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date  created;


}
