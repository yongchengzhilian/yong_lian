package com.aidou.api.vo.respone;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "身份证信息", description = "身份证信息")
@Data
public class IdCardRespone {

    @ApiModelProperty(value = "使用时间")
    private int time_used;

    @ApiModelProperty(value = "用于区分每一次请求的唯一的字符串。")
    private String request_id;

    @ApiModelProperty(value = "检测出证件的数组")
    private List<CardsBean> cards;

}
