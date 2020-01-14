package com.aidou.util.enums;

/**
 * Created by yingjiafeng on 2019/5/13.
 */
public enum UserStatusEnum {
    //-1 ： 禁止  1：需资料完善  3：单身用户  4：牵线中
    STOP("禁止登陆", -1),
    FULL_DATA_ING("资料完善中", 2),
    FULL_DATA("资料完善", 1),
    HANDS("牵手状态", 4),
    CERTIFIED("单身用户", 3);



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
