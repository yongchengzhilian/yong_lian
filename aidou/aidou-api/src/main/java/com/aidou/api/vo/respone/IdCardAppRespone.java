package com.aidou.api.vo.respone;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IdCardAppRespone {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String id_card_number;

    @ApiModelProperty(value = "返回1，代表是身份证。")
    private int type = 0;

    @ApiModelProperty(value = "表示身份证的国徽面或人像面。返回值为:front: 人像面 back: 国徽面")
    private String side;

    public IdCardAppRespone(){

    }

    public IdCardAppRespone(String name, String id_card_number, Integer type, String side) {
        this.name = name;
        this.id_card_number = id_card_number;
        this.type = type;
        this.side = side;
    }

    public IdCardAppRespone(Integer type, String side) {
        this.type = type;
        this.side = side;
    }

}
