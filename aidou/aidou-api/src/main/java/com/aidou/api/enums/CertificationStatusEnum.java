package com.aidou.api.enums;

/**
 * Created by yingjiafeng on 2019/4/30.
 */
public enum  CertificationStatusEnum {

    //认证状态  -1：未认证  1：认证中 2：拒绝  3:通过
    CERTIFICATION("认证中", 1),
    ERROR("拒绝", 2),
    SUCCESS("通过", 3);

    private String name;
    private int index;

    private CertificationStatusEnum(String name, int index) {
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
