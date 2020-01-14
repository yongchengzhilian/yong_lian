package com.aidou.util.entity.wechat;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class WechatAuthInfo implements Serializable{


	@NotNull(message="分享用户ID")
	private Long uid;
	@NotNull(message="微信code不能为空！")
	private String code;//微信登录code
	@NotNull(message="微信encryptedData不能为空！")
	private String encryptedData;//主体加密数据
	@NotNull(message="微信iv不能为空！")
	private String iv;//偏移量


}
