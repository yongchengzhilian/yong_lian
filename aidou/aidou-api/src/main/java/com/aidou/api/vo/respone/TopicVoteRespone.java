package com.aidou.api.vo.respone;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TopicVoteRespone {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "选择文本")
    private String selectName;

    @ApiModelProperty(value = "是否选择")
    private Boolean isSelect = false;

    @ApiModelProperty(value = "人数")
    private Integer num = 0;


}
