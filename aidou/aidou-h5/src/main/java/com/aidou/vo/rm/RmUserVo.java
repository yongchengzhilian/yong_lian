package com.aidou.vo.rm;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yingjiafeng on 2019/5/9.
 */
@Data
public class RmUserVo implements Serializable {
  //编号
    private Long uid;
   //昵称
    private String nikename;
    //年龄
    private Integer age;

    //联系微信
    private String wechat;

    //联系手机
    private String mobile;

    private Integer sex;
    //婚姻状态
    private String marriage;

    private Integer  status;

}
