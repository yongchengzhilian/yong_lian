package com.aidou.api.vo.request;

import lombok.Data;

/**
 * Created by yingjiafeng on 2019/6/4.
 */
@Data
public class UserCheckRequest {

    private Long uid;
    private String remarks;
    private Integer  status;


}
