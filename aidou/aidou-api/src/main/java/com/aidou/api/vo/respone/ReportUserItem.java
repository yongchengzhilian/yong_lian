package com.aidou.api.vo.respone;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/29 14:19
 */
@Data
public class ReportUserItem {
    private String id;
    private String reportUid;
    private String targetUid;
    //被举报次数
    private  Integer  targetUserReportCount;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updated;
    private String remarks;
    private List<String> imageList;

}
