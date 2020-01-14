package com.aidou.api.service.impl;

import com.aidou.api.service.NoticeService;
import com.aidou.dao.entity.TbNotice;
import com.aidou.dao.entity.TbNoticeExample;
import com.aidou.dao.mapper.TbNoticeMapper;
import com.aidou.util.enums.NoticeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private TbNoticeMapper tbNoticeMapper;

    @Override
    public void addNotice(TbNotice tbNotice) {
        tbNotice.setCreated(new Date());
        tbNoticeMapper.insertSelective(tbNotice);
    }

    @Override
    public void delNoticeByIdList(List<Long> idList) {
        TbNoticeExample example = new TbNoticeExample();
        example.createCriteria().andIdIn(idList);
        tbNoticeMapper.deleteByExample(example);
    }

    @Override
    public int selectNoticeCountByUid(Long uid, NoticeEnum noticeEnum) {
        TbNoticeExample example = new TbNoticeExample();
        example.createCriteria().andUidEqualTo(uid).andNoticeTypeEqualTo(noticeEnum.getIndex());
        List<TbNotice> tbNotices = tbNoticeMapper.selectByExample(example);
        if (tbNotices.isEmpty()) {
            return 0;
        }
        //判断消息是否过期
        List<Long> idS = new ArrayList<>();
        int count = 0;
        for (TbNotice tbNotice : tbNotices) {
            if (tbNotice.getClearTime().getTime() < new Date().getTime()) {
                //消息过期
                idS.add(tbNotice.getId());
            } else {
                count++;
            }
        }
        if (!idS.isEmpty()) {
            delNoticeByIdList(idS);
        }
        return count;
    }

    @Override
    public void deleteNoticeByUid(Long uid, NoticeEnum noticeEnum) {
        TbNoticeExample example=new TbNoticeExample();
        example.createCriteria().andUidEqualTo(uid).andNoticeTypeEqualTo(noticeEnum.getIndex());
        tbNoticeMapper.deleteByExample(example);
    }
}
