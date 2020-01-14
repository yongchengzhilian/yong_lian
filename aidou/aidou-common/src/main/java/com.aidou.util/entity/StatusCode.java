package com.aidou.util.entity;

/**
 * 状态码实体类
 */
public class StatusCode {

    public static final int OK=20000;//成功
//    public static final int MOBILE_NUMBER_VERIFICATION =20002;//绑定手机号
    public static final int ERROR =20001;//失败
    public static final int USER_STOP_LOGIN =20002;//该用户禁止登陆
    public static final int DATA_NOT_FULL =20006;//需完善资料
    public static final int USER_NAME_RENZ =20008;//需实名认证
    public static final int MOBILE_RENZ =20009;//需绑定手机号
    public static final int LOGIN=20005;//重新登录
    public static final int NOT_USER=20010;//用户不存在

}
