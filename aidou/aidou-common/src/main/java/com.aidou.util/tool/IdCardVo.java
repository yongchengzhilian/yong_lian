package com.aidou.util.tool;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yingjiafeng on 2019/6/2.
 */
@Data
public class IdCardVo implements Serializable {
   private Integer sex;

   private Date birth;

   private String  household;
   private String address;

   private String msg;


}
