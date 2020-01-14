package com.aidou.api.vo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CommentRRequest  implements Serializable {

    @NotNull(message="话题ID不能为空")
    private Long  topicId;

    @NotNull(message="评论内容不能为空！")
    private String  content;

    private Long parentid=0L;



}
