package com.aidou.api.vo.request.wechat;

import lombok.Data;

/**
 * Created by yingjiafeng on 2019/6/23.
 */
@Data
public class TemplateParam {
    private String key;
    private String value;

    public TemplateParam(String key,String value){
        this.key=key;
        this.value=value;
    }
}
