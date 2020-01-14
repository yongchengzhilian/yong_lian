package com.aidou.librec.enums;

import org.springframework.util.StringUtils;

/**
 * 最高等级是10 年龄是10
 * 用户推荐 身高比例 身高最高是  差距12
 * Created by yingjiafeng on 2019/4/29.
 */
public enum FemaleAgeRecommendEnum {

        AGE_18_24(18,24, 2),
        AGE_25_29(25,29, 3),
        AGE_30_34(30,34, 4),
        AGE_35_38(35,38, 5),
        AGE_39_42(39,42, 6),
        AGE_43_47(43,47, 7),
        AGE_48_60(48,60, 8);


    private Integer min;
    private Integer max;
    private int index;

    private FemaleAgeRecommendEnum(Integer min,Integer max, int index) {
        this.setMax(max);
        this.setMin(min);
        this.setIndex(index);
    }

    public static int getStore(int age) {

        if (StringUtils.isEmpty(age)){
            return 10;
        }

        if (age>=18 &&age<=24){
            return 2;
        }else if (age>=25 &&age<=29){
            return 3;
        }else if (age>=30 &&age<=34){
            return 4;
        }else if (age>=35 &&age<=38){
            return 5;
        }else if (age>=39 &&age<=42){
            return 6;
        }else if (age>=43 &&age<=47){
            return 7;
        }else if (age>=48 &&age<=60){
            return 3;
        }else {
            return 1;
        }

    }


    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }}
