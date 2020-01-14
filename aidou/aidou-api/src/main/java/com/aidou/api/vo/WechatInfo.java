package com.aidou.api.vo;


import lombok.Data;

@Data
public class WechatInfo {
	private String openId;
	private String nickName;
	private int gender;
	private String language;
	private String city;
	private String province;
	private String country;
	private String avatarUrl;
	private String unionId;




}
