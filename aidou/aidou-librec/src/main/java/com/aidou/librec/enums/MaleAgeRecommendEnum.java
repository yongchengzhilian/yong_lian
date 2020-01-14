package com.aidou.librec.enums;

import org.springframework.util.StringUtils;

/**
 * 最高等级是10 年龄是10
 * 用户推荐 身高比例 身高最高是  差距12
 * Created by yingjiafeng on 2019/4/29.
 */
public enum MaleAgeRecommendEnum {

    AGE_22_24(20,24, 3),
    AGE_25_29(25,29, 4),
    AGE_30_34(30,34, 5),
    AGE_35_38(35,38, 6),
    AGE_39_42(39,42, 7),
    AGE_43_47(43,47, 8),
    AGE_48_52(48,52, 9);

    public static int getStore(int age) {
        if (StringUtils.isEmpty(age)){
            return 10;
        }
        if (age>=20 &&age<=24){
            return 3;
        }else if (age>=25 &&age<=29){
            return 4;
        }else if (age>=30 &&age<=34){
            return 5;
        }else if (age>=35 &&age<=38){
            return 6;
        }else if (age>=39 &&age<=42){
            return 7;
        }else if (age>=43 &&age<=47){
            return 8;
        }else if (age>=48 &&age<=52){
            return 9;
        }else {
            return 1;
        }

    }


    private Integer min;
    private Integer max;
    private int index;

    private MaleAgeRecommendEnum(Integer min, Integer max, int index) {
        this.setMax(max);
        this.setMin(min);
        this.setIndex(index);
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
