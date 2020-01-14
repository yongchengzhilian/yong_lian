package com.aidou.util.enums;

/**
 * Created by yingjiafeng on 2019/5/13.
 */
public enum ImageStatusEnum {
    //-1 ： 禁止  1：需资料完善  2：需实名认证  3：已认证
    IMAGE_TOP("封面", "TOP"),
    IMAGE_PHOTO("相册", "PHOTO");



    private String name;
    private String index;

    private ImageStatusEnum(String name, String index) {
        this.setName(name);
        this.setIndex(index);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

}
