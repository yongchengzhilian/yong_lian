package com.aidou.api.vo.user;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yingjiafeng on 2019/6/2.
 */
@Data
public class IdCardVo implements Serializable {
   private String name;

   private Integer sex;

   private String birth;

   private String  household;


}
