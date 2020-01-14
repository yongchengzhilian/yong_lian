package com.aidou.librec.enums;

import org.springframework.util.StringUtils;

/**
 * 最高等级是10 身高是8
 * 用户推荐 身高比例 身高最高是  差距12
 * Created by yingjiafeng on 2019/4/29.
 */
public enum MaleHeightRecommendEnum {

    HEIGHT_158_162(158,162, 3),
    HEIGHT_163_166(163,166, 4),
    HEIGHT_167_170(167,170, 5),
    HEIGHT_171_174(171,174, 6),
    HEIGHT_175_178(175,178, 7),
    HEIGHT_179_184(179,184, 8),
    HEIGHT_185_189(185,189, 6);


    private Integer min;
    private Integer max;
    private int index;

    private MaleHeightRecommendEnum( Integer min,Integer max, int index) {
        this.setMax(max);
        this.setMin(min);
        this.setIndex(index);
    }

    public static Integer getStore(Integer  val){
        if (StringUtils.isEmpty(val)){
            return 10;
        }
        if (val>=158 && val<=162){
            return 3;
        }else if (val>=163 && val<=166){
            return 4;
        }else if (val>=167 && val<=170){
            return 5;
        }else if (val>=171 && val<=174){
            return 6;
        }else if (val>=175 && val<=178){
            return 7;
        }else if (val>=179 && val<=184){
            return 8;
        }else if (val>=185 && val<=189){
            return 6;
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
    }
   }
