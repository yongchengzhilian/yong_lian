package com.aidou.util.enums;

/**
 * -1:审核拒绝  1:通过 2：待审核
 *
 * @author yingjiafeng
 * @date 2019/5/13
 */
public enum NoticeEnum {


    /**
     *
     */
    NOTICE_STATUS1("牵线通知", 1),
    NOTICE_STATUS2("回复通知", 5);


    private String name;
    private Integer index;

     NoticeEnum(String name, Integer index) {
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
