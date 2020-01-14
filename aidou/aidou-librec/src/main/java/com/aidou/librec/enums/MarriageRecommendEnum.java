package com.aidou.librec.enums;

import org.springframework.util.StringUtils;

/**
 * 最高等级是10
 * 用户推荐 婚姻状态
 * Created by yingjiafeng on 2019/4/29.
 */
public enum MarriageRecommendEnum {

    HY_WH("未婚",10),
    HY_LY("离异",5),
    HY_SO("丧偶",1);


    private Integer store;
    private int index;

    private MarriageRecommendEnum(String store, int index) {
        this.setIndex(index);
    }

    public static double getStore(String marriage) {

        if (StringUtils.isEmpty(marriage)){
            return 10;
        }

        if (marriage.equalsIgnoreCase("未婚")){
            return 10;
        }else if (marriage.equalsIgnoreCase("离异")){
            return 5;
        }else {
            return 1;
        }


    }


    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }}