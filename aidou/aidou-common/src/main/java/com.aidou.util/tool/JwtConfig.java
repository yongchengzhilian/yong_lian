package com.aidou.util.tool;

public class JwtConfig {

    private Integer tokenExpirationTime=43200;//一个月
    private String tokenIssue="https://aidou.com";
    private String tokenSigningKey="xm8EV6Hy5RMFK4EEACIDAwQus1Acbdh&jr948&3gdkwowqEJne24KKjj";
    private Integer refreshTokenExpTime=60;


    public Integer getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(Integer tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public String getTokenIssuer() {
        return tokenIssue;
    }

    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssue = tokenIssuer;
    }

    public String getTokenSigningKey() {
        return tokenSigningKey;
    }

    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }

    public Integer getRefreshTokenExpTime() {
        return refreshTokenExpTime;
    }

    public void setRefreshTokenExpTime(Integer refreshTokenExpTime) {
        this.refreshTokenExpTime = refreshTokenExpTime;
    }
}
