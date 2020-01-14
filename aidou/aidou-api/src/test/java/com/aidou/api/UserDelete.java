package com.aidou.api;


import com.aidou.api.service.ReportService;
import com.aidou.api.vo.request.UserCheckRequest;
import com.aidou.dao.entity.TbFriendApplicationExample;
import com.aidou.dao.entity.TbWechatAuth;
import com.aidou.dao.mapper.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDelete {

    @Autowired
    private TbWechatAuthMapper tbWechatAuthMapper;

    @Autowired
    private TbCardMapper tbCardMapper;

    @Autowired
    private TbFriendApplicationMapper tbFriendApplicationMapper;

    @Autowired
    private TbLineMapper tbLineMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbUserContentMapper tbUserContentMapper;

    @Autowired
    private TbUserDateCheckMapper tbUserDateCheckMapper;

    @Autowired
    private TbUserImgMapper tbUserImgMapper;

    @Autowired
    private TbUserMsgMapper tbUserMsgMapper;

    @Autowired
    private ReportService reportService;


    @Test
    public void test2() {
        //100007  100010
        Long uid = 1173565791697653760L;
        tbWechatAuthMapper.deleteByPrimaryKey(uid);
        tbCardMapper.deleteByPrimaryKey(uid);
        TbFriendApplicationExample example = new TbFriendApplicationExample();
        example.createCriteria().andUseridEqualTo(uid);
        example.or().andFriendIdEqualTo(uid);
        tbFriendApplicationMapper.deleteByExample(example);
        tbLineMapper.deleteByPrimaryKey(uid);
        tbUserMapper.deleteByPrimaryKey(uid);
        tbUserContentMapper.deleteByPrimaryKey(uid);
        tbUserDateCheckMapper.deleteByPrimaryKey(uid);
        tbUserImgMapper.deleteByPrimaryKey(uid);
        tbUserMsgMapper.deleteByPrimaryKey(uid);

    }

    @Test
    public void test3() {
        UserCheckRequest check = new UserCheckRequest();
        check.setStatus(1);
        check.setRemarks("资料审核通过");
        check.setUid(100005L);
        Integer integer = reportService.checkUserInfo(check);
        System.out.println(integer);
    }

}
