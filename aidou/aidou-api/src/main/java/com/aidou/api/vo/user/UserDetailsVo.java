package com.aidou.api.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yingjiafeng on 2019/5/22.
 */
@Data
public class UserDetailsVo implements Serializable {
    private static final long serialVersionUID = -4396851340488018399L;

    private String uid;

    private Integer sex;

    /**
     * 相册
     */
    private List<String>  photo;

    private String topImage;

    //昵称
    private String nikename;
     //地址
    private String address;

    //户籍
    private String household;

    //年龄
    private String age;

    //收入
    private String income;

    //星座
    private String   constellation;
   //身高
    private Integer height;
    //工作
    private String  work;

    //婚姻情况
    private  String  marriage;

    //学历
    private String education;

    private String houseCar;

    //关于我
    private String  content;
    //兴趣爱好
    private String interest;
    private String favoriteTa;


    private String  school;


    /**
     * 是否表白
     */
    private boolean  isConfession=false;


    /**
     * 红娘微信 红娘用户才有
     */
    private String   matchmakerWechat;

    /**
     * 红娘手机号
     */
    private String  matchmakerMobile;




}
