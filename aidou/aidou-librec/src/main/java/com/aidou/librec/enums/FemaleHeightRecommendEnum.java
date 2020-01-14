package com.aidou.librec.enums;

import org.springframework.util.StringUtils;

/**
 * 最高等级是10 身高是8
 * 用户推荐 身高比例 身高最高是  差距12
 * Created by yingjiafeng on 2019/4/29.
 */
public enum FemaleHeightRecommendEnum {


    HEIGHT_152_157(152,157, 5),
    HEIGHT_158_162(158,162, 6),
    HEIGHT_163_166(163,166, 7),
    HEIGHT_167_170(167,170, 8),
    HEIGHT_171_174(171,174, 9);


    public static Integer getStore(Integer  val){
        if (StringUtils.isEmpty(val)){
            return 10;
        }

        if (val>=152 && val<=157){
            return 5;
        }else if (val>=158 && val<=162){
            return 6;
        }else if (val>=163 && val<=166){
            return 7;
        }else if (val>=167 && val<=170){
            return 8;
        }else if (val>=171 && val<=174){
            return 9;
        }else {
            return 3;
        }



    }


    private Integer min;
    private Integer max;
    private int index;

    private FemaleHeightRecommendEnum(Integer min,Integer max, int index) {
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
