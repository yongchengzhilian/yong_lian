package com.aidou.librec.enums;

import org.springframework.util.StringUtils;

/**
 * 最高等级是10 收入是10
 * 用户推荐 收入
 * Created by yingjiafeng on 2019/4/29.
 */
public enum MaleIncomeRecommendEnum {

    INCOME_10(3,10, 3),
    INCOME_10_20(10,20, 4),
    INCOME_20_30(20,30, 5),
    INCOME_30_40(30,40, 6),
    INCOME_40_50(40,50, 7),
    INCOME_50(50,100, 9);

    //30-40
    public static int getstore(String income) {
        if (StringUtils.isEmpty(income)) {
            return 10;
        }
        String[] split = income.split("-");
        if (split == null || split.length < 2) {
            return 1;
        }
        Integer mix = Integer.valueOf(split[0]);
        Integer max = Integer.valueOf(split[1]);
        if (mix >= 0 && max < 10) {
            return 3;
        } else if (mix >= 10 && max < 20) {
            return 4;
        } else if (mix >= 20 && max < 30) {
            return 5;
        } else if (mix >= 30 && max < 40) {
            return 6;
        } else if (mix >= 40 && max < 50) {
            return 7;
        } else if (mix >= 50 && max < 100) {
            return 9;
        }
        return 1;
    }


    private Integer min;
    private Integer max;
    private int index;

    private MaleIncomeRecommendEnum(Integer min, Integer max, int index) {
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
