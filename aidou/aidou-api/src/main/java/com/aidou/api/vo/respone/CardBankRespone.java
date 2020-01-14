package com.aidou.api.vo.respone;

import lombok.Data;

import java.util.List;

@Data
public class CardBankRespone {


    private String request_id;

    private int time_used;

    private String image_id;

    private List<CardBackBean> cards;



}
