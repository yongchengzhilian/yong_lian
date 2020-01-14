package com.aidou.librec.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by yingjiafeng on 2019/5/21.
 */
@Data
public class UserVo {
    private Long uid;
    private Date birthday;
    private Integer sex;
    private String income;
    private String education;
    private String houseCar;
    private String marriage;
    private String household;
    private Integer weight;
    private Integer height;

}
