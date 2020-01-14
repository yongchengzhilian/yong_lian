package com.aidou.enums;

/**
 * 红娘状态
 * Created by yingjiafeng on 2019/4/29.
 */
public enum RmStatusEnum {

    STOP("禁止登陆", -1),
    FULL_DATA("完善资料", 1),
    WAIT_CERTIFICATION("待认证", 2),
    CERTIFICATION("已认证", 3);

    private String name;
    private int index;

    private RmStatusEnum(String name, int index) {
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
