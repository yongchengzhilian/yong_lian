package com.aidou.util.entity;

import lombok.Data;

@Data
public class ProductItemVo {

    private  String  name;

    private String  price;

    private  Integer  num;

    public ProductItemVo(String name, String price, Integer num) {
        this.name = name;
        this.price = price;
        this.num = num;
    }
}
