package com.aidou.vo.rm;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Data
public class UserInfoEditDto {
    //编辑需要添加UID
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long  uid;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nikename;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date  birthday;
    //相册
    private List<String>  photo;
    //性别
    private Integer sex=1;
    //收入
    @NotNull(message="收入不能为空！")
    private String income;

    @NotNull(message = "职业不能为空!")
    private String work;

    private String wechat;


    //学历
    @NotNull(message="学历不能为空！")
    private String education;
    //房车
    @NotNull(message="车房不能为空！")
    private String houseCar;
    //婚姻
    @NotNull(message="婚姻情况不能为空！")
    private String marriage;
    //档案
    @NotNull(message="现住不能为空！")
    private String city;//现住 市
    @NotNull(message="现住不能为空！")
    private String province;//现住 省份
    @NotNull(message="现住不能为空！")
    @Min(value=30,message="体重不能小于30公斤")
    @Max(value = 100,message = "体重不能大于100公斤")
    private int weight;
    @NotNull(message="身高不能为空！")
    @Min(value=140,message="身高不能小于140")
    @Max(value = 200,message = "身高不能大于200")
    private int height;
   //自我介绍
   @NotNull(message="关于我不能为空！")
    private String  content;
   //兴趣爱好
    private String  interest;
    //心仪的ta
    private String  favoriteTa;

}
