package com.aidou.api.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/9/5 17:34
 */
@Data
public class QrCodeRequest  implements Serializable {
  private String   scene;
  private String  page;
  private Integer width=430;

}
