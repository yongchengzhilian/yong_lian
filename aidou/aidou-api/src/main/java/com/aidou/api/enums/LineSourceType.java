package com.aidou.api.enums;

/**
 * RED红线   系统赠送
 * GOLD 金线  花钱购买
 */
public enum LineSourceType {
    GOLD("GOLD", "付费购买"),
    BACK("BACK", "系统回退"),
    SHARE_GIFT("SHARE_GIFT", "分享获得"),
    GIFT("GIFT", "系统赠送");
    private String name;
    private String index;

    private LineSourceType(String name, String index) {
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
