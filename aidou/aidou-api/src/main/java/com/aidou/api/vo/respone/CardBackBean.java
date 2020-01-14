package com.aidou.api.vo.respone;

import lombok.Data;

@Data
public class CardBackBean {

    /**
     * issued_by : 宁波市公安局海曙分局
     * valid_date : 2018.08.21-2038.08.21
     * type : 1
     * side : back
     */

    private String issued_by;
    private String valid_date;
    private int type;
    private String side;


}
