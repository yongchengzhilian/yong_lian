package com.aidou.api.vo.respone;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CardsBean {
    @ApiModelProperty(value = "性别（男/女）")
    private String gender;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String id_card_number;

    @ApiModelProperty(value = "生日，格式为YYYY-MM-DD")
    private String birthday;

    @ApiModelProperty(value = "民族（汉字）")
    private String race;

    @ApiModelProperty(value = "住址")
    private String address;

    @ApiModelProperty(value = "返回1，代表是身份证。")
    private int type = 0;

    @ApiModelProperty(value = "表示身份证的国徽面或人像面。返回值为:front: 人像面 back: 国徽面")
    private String side;

}
