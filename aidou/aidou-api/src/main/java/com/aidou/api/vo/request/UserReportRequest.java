package com.aidou.api.vo.request;

import lombok.Data;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/29 14:08
 */
@Data
public class UserReportRequest {
  /**
   * 审核ID
   */
  private Long id ;
    /**
     * 忽略:2 封号:1 警告:0
     */
  private Integer type;

  private String  remarks;

}
