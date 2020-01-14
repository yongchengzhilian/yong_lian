package com.aidou.util.entity.wechat;


/**
 * 用户授权token
 */
public class UserAccessToken {

	// 获取到的凭证
	private String session_key;
	private String openid;
	private String unionid;
	private WechatAuthInfo info;
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	
	public WechatAuthInfo getInfo() {
		return info;
	}
	public void setInfo(WechatAuthInfo info) {
		this.info = info;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

}
