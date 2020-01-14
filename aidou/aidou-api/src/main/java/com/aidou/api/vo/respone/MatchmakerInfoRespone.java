package com.aidou.api.vo.respone;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class MatchmakerInfoRespone  implements Serializable {

   //是否红娘
   private boolean  isMatchmaker=false;
   @ApiModelProperty(value = "区域")
   @NotNull(message="区域不能为空！")
   private String  area;

   @ApiModelProperty(value = "手机号")
   @NotNull(message="手机号不能为空！")
   private String   mobile;
   @ApiModelProperty(value = "微信号")
   private String   wechat;
   @ApiModelProperty(value = "身份证号")
   @NotNull(message="身份证号不能为空！")
   private String  idCard;

   @ApiModelProperty(value = "姓名")
   @NotNull(message="姓名不能为空！")
   private String  name;




}
