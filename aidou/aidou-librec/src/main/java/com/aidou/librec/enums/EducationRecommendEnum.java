package com.aidou.librec.enums;

import org.springframework.util.StringUtils;

/**
 * 最高等级是5
 * 用户推荐 学历
 * Created by yingjiafeng on 2019/4/29.
 */
public enum EducationRecommendEnum {

    RECOMMEND_GAO("其他" , 1),
    RECOMMEND_ZHUAN("专科", 4),
    RECOMMEND_BEN("本科", 5),
    RECOMMEND_YAN("研究生", 6),
    RECOMMEND_BO("博士",  7);


    private String store;
    private int index;

    private EducationRecommendEnum(String store, int index) {
        this.store=store;
        this.setIndex(index);
    }

    public static int getStore(String education) {
        if (StringUtils.isEmpty(education)){
            return 7;
        }


        if (education.equalsIgnoreCase("其他")){
            return 1;
        }else if (education.equalsIgnoreCase("专科")){
            return 4;
        }else if (education.equalsIgnoreCase("本科")){
            return 5;
        }else if (education.equalsIgnoreCase("研究生")){
            return 6;
        }else if (education.equalsIgnoreCase("博士")){
            return 7;
        }
        return 1;

    }


    public String  getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }}