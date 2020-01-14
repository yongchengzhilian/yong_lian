package com.aidou.enums;

/**
 * 红娘状态
 * Created by yingjiafeng on 2019/4/29.
 */
public enum UserStatusEnum {
//-1:禁止登陆 1:待认证 2：认证中 3：认证失败  4：已认证 5：牵线中
    STOP("禁止登陆", -1),
    FULL_DATA("待认证", 1),
    WAIT_CERTIFICATION("认证中", 2),
    ERROR_CERTIFICATION("认证失败", 1),
    SUCCESS_CERTIFICATION("已认证", 1),
    MATCH_LINE("牵线中", 1);


    private String name;
    private int index;

    private UserStatusEnum(String name, int index) {
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
