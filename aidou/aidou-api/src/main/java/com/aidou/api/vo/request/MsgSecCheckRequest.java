package com.aidou.api.vo.request;

import com.aidou.util.tool.GsonUtil;
import lombok.Data;

public class MsgSecCheckRequest {

  private String  content;
  public MsgSecCheckRequest(String content){
      this.content=content;
  }

    @Override
    public String toString() {
        return GsonUtil.gsonString(this);
    }
}
