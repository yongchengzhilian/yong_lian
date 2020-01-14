package com.aidou.api.service;

import com.aidou.dao.entity.TbNotice;
import com.aidou.util.enums.NoticeEnum;

import java.util.List;

public interface NoticeService {

    /**
     * 添加一条通知
     *
     * @param tbNotice
     */
    void addNotice(TbNotice tbNotice);


    /**
     * 删除通知
     *
     * @param idList
     */
    void delNoticeByIdList(List<Long> idList);


    /**
     * 根据用户ID获取通知数量
     * @param uid        用户ID
     * @param noticeEnum 消息类型
     * @return
     */
    int selectNoticeCountByUid(Long uid, NoticeEnum noticeEnum);


    /**
     * 删除通知
     * @param uid  用户ID
     * @param noticeEnum  通知类型
     */
    void deleteNoticeByUid(Long uid, NoticeEnum noticeEnum);


}
