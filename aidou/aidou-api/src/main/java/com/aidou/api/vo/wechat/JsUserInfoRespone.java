package com.aidou.api.vo.wechat;

import lombok.Data;

@Data
public class JsUserInfoRespone {


    /**
     * subscribe : 1
     * openid : ov3pU5we2vsycU137GgzTP4IX00A
     * nickname : night🙄 🙄 🙄
     * sex : 1
     * language : zh_CN
     * city : 宁波
     * province : 浙江
     * country : 中国
     * headimgurl : http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM78MnTvUCziaXHU9W0phWxaNRI7AibzUsCs1piaicJ1rcMuWELia7T6mXpCbtFxcrz81mH5LyvQkbABeIPMwSEGwSMhg8UGWWa8a50s/132
     * subscribe_time : 1567056347
     * unionid : oVByEw4h9rF1GA1_Q_g4fFVprrX0
     * remark :
     * groupid : 0
     * tagid_list : []
     * subscribe_scene : ADD_SCENE_SEARCH
     * qr_scene : 0
     * qr_scene_str :
     */
    private int subscribe;
    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private int subscribe_time;
    private String unionid;
    private String remark;
    private int groupid;
    private String subscribe_scene;
    private int qr_scene;
    private String qr_scene_str;
}
