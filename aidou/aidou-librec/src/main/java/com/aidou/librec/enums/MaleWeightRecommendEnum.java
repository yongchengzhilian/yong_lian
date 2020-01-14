package com.aidou.librec.enums;

import org.springframework.util.StringUtils;

/**
 * 最高等级是10 体重是8
 * 用户推荐 身高比例 身高最高是  差距12
 * Created by yingjiafeng on 2019/4/29.
 */
public enum MaleWeightRecommendEnum {



    WEIGHT_56_59(56,59, 3),
    WEIGHT_60_63(60,63, 4),
    WEIGHT_64_67(64,67, 5),
    WEIGHT_68_71(68,71, 6),
    WEIGHT_72_76(72,76, 7),
    WEIGHT_76_80(76,80, 8),
    WEIGHT_80(80,100, 10);


    private Integer min;
    private Integer max;
    private int index;


    public static int getStore(Integer weight) {
        if (StringUtils.isEmpty(weight)){
            return 10;
        }
        if (weight>=56 && weight<=59){
            return 3;
        }else if (weight>=60 && weight<=63){
            return 4;
        }else if (weight>=64 && weight<=67){
            return 5;
        }else if (weight>=68 &&weight<=71){
            return 6;
        }else if (weight>=72 && weight<=76){
            return 7;
        }else if (weight>=76 && weight<=80){
            return 8;
        }else {
            return 10;
        }

    }


    private MaleWeightRecommendEnum(Integer min, Integer max, int index) {
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
    }


    }
