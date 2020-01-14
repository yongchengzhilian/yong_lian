package com.aidou.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by yingjiafeng on 2019/6/3.
 */

@ApiModel(value = "MatchmakerUserInfoRequest", description = "用户资料")
@Data
public class MatchmakerUserInfoRequest {

    private String nikename;

    @ApiModelProperty(value = "生日")
    @NotNull(message="户籍不能为空！")
    private Date birthday;


    @ApiModelProperty(value = "性别 男 1  女2")
    @Min(value=1,message="男生1")
    @Max(value = 2,message = "女生2")
    @NotNull(message="户籍不能为空！")
    private Integer  sex;



    @ApiModelProperty(value = "户籍")
    @NotNull(message="户籍不能为空！")
    private String household;


    @ApiModelProperty(value = "收入")
    @NotNull(message="收入不能为空！")
    private String income;
    @ApiModelProperty(value = "学历")
    @NotNull(message="学历不能为空！")
    private String education;
    @ApiModelProperty(value = "车房")
    @NotNull(message="车房不能为空！")
    private String houseCar;
    @ApiModelProperty(value = "婚姻")
    @NotNull(message="婚姻情况不能为空！")
    private String marriage;

    @ApiModelProperty(value = "现居")
    @NotNull(message="现居不能为空！")
    private String town;



    @ApiModelProperty(value = "体重")
    @NotNull(message="现住不能为空！")
    @Min(value=30,message="体重不能小于30公斤")
    @Max(value = 100,message = "体重不能大于100公斤")
    private Integer weight;

    @ApiModelProperty(value = "身高")
    @NotNull(message="身高不能为空！")
    @Min(value=140,message="身高不能小于140")
    @Max(value = 200,message = "身高不能大于200")
    private Integer height;

    @ApiModelProperty(value = "职业")
    @NotNull(message="职业不能为空！")
    private String work;
    @ApiModelProperty(value = "自我介绍")
    @NotNull(message="自我介绍不能为空！")
    private String content;

    @ApiModelProperty(value = "兴趣爱好")
    @NotNull(message="兴趣爱好不能为空！")
    private String interest;





    @ApiModelProperty(value = "折偶需求")
    @NotNull(message="折偶需求不能为空！")
    private String favoriteTa;

    @ApiModelProperty(value = "用户首图")
    @NotNull(message="用户首图不能为空！")
    private String topImage;

    @ApiModelProperty(value = "相册")
    private List<String>  photo;



}
