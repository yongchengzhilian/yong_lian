package com.aidou.librec.enums;

import org.springframework.util.StringUtils;

/**
 * 最高等级是10 体重是8
 * 用户推荐 身高比例 身高最高是  差距12
 * Created by yingjiafeng on 2019/4/29.
 */
public enum FemaleWeightRecommendEnum {
    WEIGHT_40_43(40,43, 3),
    WEIGHT_44_46(44,46, 4),
    WEIGHT_47_50(47,50, 5),
    WEIGHT_51_53(51,53, 6),
    WEIGHT_54_56(54,56, 7),
    WEIGHT_57_60(57,60, 8),
    WEIGHT_60(61,100, 9);


    private Integer min;
    private Integer max;
    private int index;

    private FemaleWeightRecommendEnum(Integer min, Integer max, int index) {
        this.setMax(max);
        this.setMin(min);
        this.setIndex(index);
    }

    public static int getStore(Integer weight) {

        if (StringUtils.isEmpty(weight)){
            return 10;
        }

        if (weight>=40 && weight<=43){
            return 3;
        }else if (weight>=44 && weight<=46){
            return 4;
        }else if (weight>=47 && weight<=50){
            return 5;
        }else if (weight>=51 &&weight<=53){
            return 6;
        }else if (weight>=54 && weight<=56){
            return 7;
        }else if (weight>=57 && weight<=60){
            return 8;
        }else {
            return 9;
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
    }
}
