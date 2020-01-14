package com.aidou.api.controller.friend;

import com.aidou.api.controller.BaseController;
import com.aidou.api.service.FriendApplicationService;
import com.aidou.api.service.FriendRecommendService;
import com.aidou.api.vo.user.ReommendUserVo;
import com.aidou.api.vo.user.UserDetailsVo;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.exception.WechatAesDesryptException;
import com.aidou.util.exception.WechatTokenNotFoundException;
import com.aidou.util.tool.GsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yingjiafeng on 2019/5/20.
 */
@Slf4j
@Api(tags = "用户（非登陆操作）")
@RestController
@RequestMapping("/friend")
public class FriendRecommendController  extends BaseController {

    @Autowired
    private FriendRecommendService  friendRecommendService;

    @Autowired
    private FriendApplicationService  friendApplicationService;


    @ApiOperation(value = "用户详情", notes = "用户推荐")
    @PostMapping(value = "descript/{uid}")
    public AidouResult getUserDetails(@PathVariable Long uid){
        try {
            UserDetailsVo userDetailsVo = friendRecommendService.findUserDescriptByUid(uid);
            //判断是否有表白
            boolean confession = friendApplicationService.isConfession(uid);
            userDetailsVo.setConfession(confession);
            return   AidouResult.success(userDetailsVo);
        } catch (Exception e) {
            e.printStackTrace();
            return AidouResult.error("获取数据失败");
        }
    }





}
