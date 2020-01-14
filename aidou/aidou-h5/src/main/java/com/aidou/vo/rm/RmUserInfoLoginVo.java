package com.aidou.vo.rm;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yingjiafeng on 2019/4/29.
 */
@Data
public class RmUserInfoLoginVo implements Serializable {


   private String areal;
   private String nikeName;
   private String  avatarurl;
   //1:待完善资料  2:待认证  3:认证中  4：已认证
   private Integer status;
   //认证状态
   //认证状态 1：认证中 2：拒绝  3:通过
//   private Integer certificationStatus;
   //上次登陆
   private Date lastTimel;

   private String token;

}
