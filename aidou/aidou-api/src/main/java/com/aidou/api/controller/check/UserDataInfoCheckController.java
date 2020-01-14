package com.aidou.api.controller.check;

import com.aidou.api.service.ReportService;
import com.aidou.api.service.UserCheckInfoService;
import com.aidou.api.vo.request.UserCheckRequest;
import com.aidou.api.vo.respone.UserDateCheckRespone;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/29 9:35
 */

@Slf4j
@Api(tags = "资料审核")
@RestController
@RequestMapping("/check")
public class UserDataInfoCheckController {

    @Autowired
    private UserCheckInfoService userCheckInfoService;

    @Autowired
    private ReportService reportService;

    @ApiOperation(value = "用户资料审核", notes = "用户资料审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户ID", required = true),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true),
            @ApiImplicitParam(name = "status", value = "审核状态(-1:审核拒绝  1:通过 )", required = true)
    }
    )
    @PostMapping(value = "/userinfo/check")
    public AidouResult userInfoCheck(@RequestBody UserCheckRequest checkRequest){
        try {
            reportService.checkUserInfo(checkRequest);
            return AidouResult.success();
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }




    @ApiOperation(value = "资料审核列表", notes = "资料审核列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true),
            @ApiImplicitParam(name = "status", value = "-1:审核拒绝  1:通过 2：待审核 ", required = true),
    })
    @PostMapping(value = "/user/info/list/{page}/{status}")
    public AidouResult userInfoList(@PathVariable Integer page,@PathVariable Integer status){
        try {
            List<UserDateCheckRespone> allCheckInfoList = userCheckInfoService.findAllCheckInfoList(page, 10, status);
            return AidouResult.success(allCheckInfoList);
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }

}
