package com.aidou.api.vo.wechat;

import lombok.Data;

/**
 * Created by yingjiafeng on 2019/5/16.
 */
@Data
public class WechatAccountToken {

    /**
     * access_token : 21_r7MlV2IUgBMfvmy6Clvn0hhf4x-s3m4yzlr2DUal8uw5-na5GTyKS_Ft4GAWx0-X4qTXK6fT_yUaEE1eSaV4F-tsoIuPtQVLmncsxDKU2N229eTh4GVefpjc_mR9Nz7QGTxgBUDFWiHA6P2yZATeABANOH
     * expires_in : 7200
     */

    private String access_token;
    private int expires_in;

}
