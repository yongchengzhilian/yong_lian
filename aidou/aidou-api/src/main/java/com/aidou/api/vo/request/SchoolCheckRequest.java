package com.aidou.api.vo.request;

import lombok.Data;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/18 11:45
 */
@Data
public class SchoolCheckRequest extends UserCheckRequest {
    private String  school;

    private String  education;

}
