package com.aidou.api.vo.respone;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yingjiafeng on 2019/6/23.
 */
@Data
public class WxAccountTokenRespone implements Serializable {


    /**
     * access_token : 22_m-L7_BGQrwwVI9_owx0r1wQW4KCTlIeJYRsv_2tvGoUj_2colFTUvATI26hxvGfU34Cd2ZfM7mAVQ6_jyUPscMqQL54h3DCHOAxbGfRSj9yaI5QxgkvuexKb-vl00yJ2HEecY0N7NBNgrySrEDVfAFAHGY
     * expires_in : 7198
     */

    private String access_token;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
