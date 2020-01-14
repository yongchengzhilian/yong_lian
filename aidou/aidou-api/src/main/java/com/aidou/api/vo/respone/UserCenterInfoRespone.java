package com.aidou.api.vo.respone;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by yingjiafeng on 2019/6/20.
 */
@Data
public class UserCenterInfoRespone  implements Serializable {


    @JsonSerialize(using = ToStringSerializer.class)
    private Long uid;

    //首图
    private String  topImage;

    private String nikeName;

    //我喜欢
    private Integer  likeCount;

    //喜欢我
    private Integer likeMeCount;

    //互相喜欢
    private Integer likeEachCount;

    //学校
    private String school;

    //学历
    private   String  education;


}
