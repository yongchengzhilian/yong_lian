package com.aidou.api.enums;

/**
 * 微信消息通知模板
 * Created by yingjiafeng on 2019/4/29.
 */
public enum WechatTemplateEnum {

    /**
     * 微信模板 审核通知
     */
    TEMPLATE_1("审核结果通知", "HN8Io6y0HHkyCZwkBF6XvLKeecxspv0W601s640eosA"),
    TEMPLATE_2("牵线结果通知", "Xog069rTvytwulNvY8tdDooykmulHTom2atyRLgPlT8"),
    TEMPLATE_3("牵线请求通知", "6y7BSPpc-P6T1lubq45n9hQ09keOYyUK9_b9GRFtkN8"),
    TEMPLATE_4("留言回复通知", "VlJPDRp9isPnvXQ968YjnB-pSXObVc-RfQZAkDq345U"),
    TEMPLATE_6("提醒发送成功通知 ", "Ul_SDY_jLD9K99vDxlFXXZux9rhy4LAzCFfXpAGhmYY"),
    TEMPLATE_7("匹配失败通知 ", "YuSbkp4KjrVSOmYxMxdb8MQsdYvUhsEGo-M17cirytg"),
    TEMPLATE_5("活动报名通知", "xvyR7LYABf_7n9T9mn2DggAhSBtQJca-NxUin5CvmyQ");

    /**
     * 模板名
     */
    private String name;
    /**
     * 模板ID
     */
    private String index;

     WechatTemplateEnum(String name, String index) {
        this.setName(name);
        this.setIndex(index);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }


}
