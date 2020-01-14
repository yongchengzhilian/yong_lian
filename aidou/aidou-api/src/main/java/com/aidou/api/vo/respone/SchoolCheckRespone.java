package com.aidou.api.vo.respone;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/29 10:40
 */
@Data
public class SchoolCheckRespone {

  private String uid;

  private String name="未认证";

  private String idCard="未认证";

  private Integer status;

  private String   verificationCode;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date  created;

}
