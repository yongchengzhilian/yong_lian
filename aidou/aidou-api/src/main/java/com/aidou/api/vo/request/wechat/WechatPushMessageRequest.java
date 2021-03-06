package com.aidou.api.vo.request.wechat;

import lombok.Data;

import java.util.List;

/**
 * Created by yingjiafeng on 2019/6/23.
 */
@Data
public class WechatPushMessageRequest {
    private String touser;
    private String template_id;
    private String page;
    private List<TemplateParam> templateParamList;



    public String toJSON() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        buffer.append(String.format("\"touser\":\"%s\"", this.touser)).append(",");
        buffer.append(String.format("\"template_id\":\"%s\"", this.template_id)).append(",");
        buffer.append(String.format("\"page\":\"%s\"", this.page)).append(",");
        buffer.append("\"data\":{");
        TemplateParam param = null;
        for (int i = 0; i < this.templateParamList.size(); i++) {
            param = templateParamList.get(i);
            // 判断是否追加逗号
            if (i < this.templateParamList.size() - 1){
                buffer.append(String.format("\"%s\": {\"value\":\"%s\"},", param.getKey(), param.getValue()));
            }else{
                buffer.append(String.format("\"%s\": {\"value\":\"%s\"}", param.getKey(), param.getValue()));
            }
        }
        buffer.append("}");
        buffer.append("}");
        return buffer.toString();
    }
}
