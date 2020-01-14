package com.aidou.librec.enums;

import org.springframework.util.StringUtils;

/**
 * 最高等级是5
 * 用户推荐 学历
 * Created by yingjiafeng on 2019/4/29.
 */
public enum CarHouseRecommendEnum {

    CAR_HOUSE_1("有车有房" , 10),
    CAR_HOUSE_2("有车没房", 5),
    CAR_HOUSE_3("有房没车", 8),
    CAR_HOUSE_4("有房没车", 2);

    private Integer store;
    private int index;

    private CarHouseRecommendEnum(String store, int index) {
        this.setIndex(index);
    }

    public static int getStore(String education) {
        if (StringUtils.isEmpty(education)){
            return 10;
        }

        if (education.equalsIgnoreCase("有车有房")){
            return 10;
        }else if (education.equalsIgnoreCase("有车没房")){
            return 5;
        }else if (education.equalsIgnoreCase("有房没车")){
            return 8;
        }else if (education.equalsIgnoreCase("没房没车")){
            return 2;
        }
        return 1;


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