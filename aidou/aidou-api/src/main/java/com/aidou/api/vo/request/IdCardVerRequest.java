package com.aidou.api.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by yingjiafeng on 2019/6/2.
 */
@Data
public class IdCardVerRequest implements Serializable {

    @ApiModelProperty(name="身份证号码")
    @NotNull(message = "身份证号码不能为空！")
    private String idCard;

    @ApiModelProperty(name="姓名")
    @NotNull(message = "姓名不能为空！")
    private String name;

    @ApiModelProperty(name="身份证正面")
    @NotNull(message = "身份证正面不能为空！")
    private String   idcardFront;

    @ApiModelProperty(name="身份证反面")
    @NotNull(message = "身份证反面不能为空！")
    private String   idcardFan;

}
