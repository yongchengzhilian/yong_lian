package com.aidou.api.vo.user;

import lombok.Data;

/**
 * Created by yingjiafeng on 2019/5/20.
 */
@Data
public class ReommendUserVo  {
    private String uid;
    private  String  topImage;
    private String nikename;
    private String address;
    private String age;
    private Integer sex;
    //星座
    private String   constellation;
    private Integer height;
    private String  work;
    //介绍
    private String  content;
    private String lastTime;
    private String face;
    private String houseCar;
    private String education;
    private String marriage;

}
