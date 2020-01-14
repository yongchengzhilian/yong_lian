package com.aidou.api.controller.check;

import com.aidou.api.service.ReportService;
import com.aidou.api.service.UserCheckInfoService;
import com.aidou.api.vo.request.SchoolCheckRequest;
import com.aidou.api.vo.respone.SchoolCheckRespone;
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
@Api(tags = "学历审核")
@RestController
@RequestMapping("/check")
public class SchoolCheckController {

    @Autowired
    private UserCheckInfoService userCheckInfoService;

    @Autowired
    private ReportService reportService;


    @ApiOperation(value = "学历审核", notes = "学历审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户ID", required = true),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true),
            @ApiImplicitParam(name = "school", value = "学校", required = true),
            @ApiImplicitParam(name = "education", value = "学历", required = true),
            @ApiImplicitParam(name = "status", value = "审核状态(-1:审核拒绝  1:通过 )", required = true)
    }
    )
    @PostMapping(value = "/school/check")
    public AidouResult schoolVerificationCheck(@RequestBody SchoolCheckRequest schoolCheckRequest){
        try {
            reportService.schoolCheck(schoolCheckRequest);
            return  AidouResult.success();
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }



    @ApiOperation(value = "学历审核列表", notes = "学历审核列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true),
            @ApiImplicitParam(name = "status", value = "是否学历认证   -1：未认证 1:认证中  2：拒绝   3： 已认证  5:已审核", required = true),
    })
    @PostMapping(value = "/user/school/list/{page}/{status}")
    public AidouResult userSchoolList(@PathVariable Integer page,@PathVariable Integer status){
        try {
           List<SchoolCheckRespone>  checkResponeList=   userCheckInfoService.findAllSchoolList(page, 10, status);
            return AidouResult.success(checkResponeList);
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }

}
