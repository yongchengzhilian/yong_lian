package com.aidou.api.vo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by yingjiafeng on 2019/6/3.
 */
@Data
public class ReportUserRequest   {

    @NotNull(message="举报用户不能为空！")
    private Long  reportUid;

    @NotNull(message="举报原因不能为空！")
    private String   remarks;

    private List<String>  photo;


}
