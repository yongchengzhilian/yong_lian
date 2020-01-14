package com.aidou.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by yingjiafeng on 2019/6/3.
 */

@ApiModel(value = "User", description = "用户资料")
@Data
public class UserInfoRequest {


    @ApiModelProperty(value = "微信号")
    @NotNull(message = "微信号不能为空！")
    private String wechat;
    @ApiModelProperty(value = "收入")
    @NotNull(message = "收入不能为空！")
    private String income;
    @ApiModelProperty(value = "学历")
    @NotNull(message = "学历不能为空！")
    private String education;
    @ApiModelProperty(value = "车房")
    @NotNull(message = "车房不能为空！")
    private String houseCar;
    @ApiModelProperty(value = "婚姻")
    @NotNull(message = "婚姻情况不能为空！")
    private String marriage;

    @ApiModelProperty(value = "现居")
    @NotNull(message = "现居不能为空！")
    private String town;

    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号！")
    private String mobile;

    @ApiModelProperty(value = "体重")
    @NotNull(message = "现住不能为空！")
    @Min(value = 30, message = "体重不能小于30公斤")
    @Max(value = 200, message = "体重不能大于200公斤")
    private Integer weight;

    @ApiModelProperty(value = "身高")
    @NotNull(message = "身高不能为空！")
    @Min(value = 140, message = "身高不能小于140")
    @Max(value = 200, message = "身高不能大于200")
    private Integer height;

    @ApiModelProperty(value = "职业")
    @NotNull(message = "职业不能为空！")
    private String work;
    @ApiModelProperty(value = "自我介绍")
    @NotNull(message = "自我介绍不能为空！")
    private String content;

    @ApiModelProperty(value = "兴趣爱好")
    @NotNull(message = "兴趣爱好不能为空！")
    private String interest;

    @ApiModelProperty(value = "折偶需求")
    @NotNull(message = "折偶需求不能为空！")
    private String favoriteTa;

    @ApiModelProperty(value = "用户首图")
    @NotNull(message = "用户首图不能为空！")
    private String topImage;

    @ApiModelProperty(value = "相册")
    private List<String> photo;
}
