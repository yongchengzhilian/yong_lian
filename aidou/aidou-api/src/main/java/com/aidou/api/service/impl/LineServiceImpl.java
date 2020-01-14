package com.aidou.api.service.impl;

import com.aidou.api.enums.LineSourceType;
import com.aidou.api.service.LineService;
import com.aidou.dao.entity.TbLine;
import com.aidou.dao.entity.TbLineExample;
import com.aidou.dao.entity.TbLineRecord;
import com.aidou.dao.mapper.TbLineMapper;
import com.aidou.dao.mapper.TbLineRecordMapper;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by yingjiafeng on 2019/5/22.
 */
@Service
public class LineServiceImpl implements LineService {

    @Autowired
    private TbLineMapper tbLineMapper;

    @Autowired
    private TbLineRecordMapper tbLineRecordMapper;

    /**
     * 根据类型获取红线
     *
     * @param uid
     * @param sourceType
     * @return
     */
    private TbLine findUserLineByType(Long uid, LineSourceType sourceType) {
        TbLineExample example = new TbLineExample();
        example.createCriteria().andUserIdEqualTo(uid)
                .andTypeEqualTo(sourceType.getName());
        List<TbLine> tbLines = tbLineMapper.selectByExample(example);
        return tbLines.isEmpty() ? null : tbLines.get(0);

    }


    @Override
    public Integer findRedCountByUid(Long uid) throws BizException {
        List<TbLine> lineByUid = findLineByUid(uid);
        if (lineByUid.isEmpty()) {
            return 0;
        }
        Integer count = 0;
        for (int i = 0; i < lineByUid.size(); i++) {
            count += lineByUid.get(i).getCount();
        }
        return count;
    }

    @Transactional(rollbackFor = BizException.class)
    @Override
    public Integer addLine(Long uid, LineSourceType lineSourceType, Integer num) throws BizException {
        //判断是否有同类型红线
        int count = -1;
        TbLine tbLine = findUserLineByType(uid, lineSourceType);
        if (tbLine != null) {
            tbLine.setCount(tbLine.getCount() + num);
            tbLine.setUpdated(new Date());
            count = tbLineMapper.updateByPrimaryKeySelective(tbLine);
        } else {
            TbLine line = new TbLine();
            line.setUserId(uid);
            line.setType(lineSourceType.getName());
            line.setSource(lineSourceType.getIndex());
            line.setCreated(new Date());
            line.setCount(num);
            line.setUpdated(new Date());
            count = tbLineMapper.insertSelective(line);
        }
        addLineLog(uid, lineSourceType.getIndex(), num);
        return count;
    }


    @Override
    public List<TbLine> findLineByUid(Long uid) throws BizException {
        Optional.ofNullable(uid).orElseThrow(NullPointerException::new);
        TbLineExample example = new TbLineExample();
        example.setOrderByClause("updated asc");
        example.createCriteria().andUserIdEqualTo(uid).andStatusEqualTo(1);
        List<TbLine> tbLines = tbLineMapper.selectByExample(example);
        return tbLines;
    }

    @Transactional(rollbackFor = BizException.class)
    @Override
    public void deleteLineByUid(Long toId, String remarks) throws BizException {
        List<TbLine> lineByUid = findLineByUid(CurrentUser.get().getId());
        if (lineByUid.isEmpty()) {
            throw new BizException("您还没有红线");
        }
        TbLine tbLine = lineByUid.get(0);
        Integer count = tbLine.getCount();
        if (count == 1) {
            tbLineMapper.deleteByPrimaryKey(tbLine.getId());
        } else {
            tbLine.setCount(tbLine.getCount() - 1);
            tbLine.setUpdated(new Date());
            tbLineMapper.updateByPrimaryKeySelective(tbLine);
        }
        //记录
        removeLineLog(toId, remarks, 1);
    }

    private void removeLineLog(Long toId, String msg, Integer num) {
        TbLineRecord logRecord = new TbLineRecord();
        logRecord.setCreated(new Date());
        logRecord.setUid(CurrentUser.get().getId());
        logRecord.setToid(toId);
        logRecord.setNum(num);
        logRecord.setType("remove");
        logRecord.setReason(msg);
        tbLineRecordMapper.insertSelective(logRecord);
    }

    private void addLineLog(Long uid, String msg, Integer num) {
        TbLineRecord logRecord = new TbLineRecord();
        logRecord.setCreated(new Date());
        logRecord.setUid(uid);
        logRecord.setNum(num);
        logRecord.setToid(10086L);
        logRecord.setType("add");
        logRecord.setReason(msg);
        tbLineRecordMapper.insertSelective(logRecord);
    }
}
