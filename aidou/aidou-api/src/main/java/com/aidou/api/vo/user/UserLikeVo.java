package com.aidou.api.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yingjiafeng on 2019/5/25.
 */
@Data
public class UserLikeVo implements Serializable {

    //消息ID
    private Long id;
    private String age;
    private Integer weight;
    //星座
    private String constellation;
    private Integer height;
    private String work;
    //用户ID
    private String uid;
    private String image;
    private String nikeName;
    private String address;
    private String wechat;
    //留言
    private String content;
    //收入
    private String income;
    private String houseCar;
    //婚姻状态
    private String marriage;

    //姓名
    private String  name;

    //户籍
    private String  household;
   //学校
    private String  school;

    //学历
    private String education;
    //学历认证状态   -1：未认证  1：认证中 2：拒绝  3:通过
    private Integer  schoolVerification;

    private String  created;

}
