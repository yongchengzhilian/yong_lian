package com.aidou.util.enums;

/**
 * -1:审核拒绝  1:通过 2：待审核
 * Created by yingjiafeng on 2019/5/13.
 */
public enum CheckStatusEnum {
    //-1 ： 禁止  1：需资料完善  2：需实名认证  3：已认证
    //
    CHECK_ING("待审核", 2),
    CHECK("审核通过", 1),
    CHECK_FAIL("审核拒绝", -1);



    private String name;
    private Integer index;

    private CheckStatusEnum(String name, Integer index) {
        this.setName(name);
        this.setIndex(index);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return this.index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

}
