package com.aidou.enums;

/**
 * 红娘状态
 * Created by yingjiafeng on 2019/4/29.
 */
public enum ImageTypeEnum {

    PHOTO("相册", 11);


    private String name;
    private int index;

    private ImageTypeEnum(String name, int index) {
        this.setName(name);
        this.setIndex(index);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
