package com.aidou.api.vo.request;

import com.aidou.api.vo.PageExtAction;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class ArticleCommentSearchRequest extends PageExtAction {

    @NotNull(message = "话题ID不能为空！")
    private Long articleId;

    @ApiModelProperty(value = "1:最新  2 :最热")
    private Integer  type;
}
