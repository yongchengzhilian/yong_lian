package com.aidou.api.controller.check;

import com.aidou.api.controller.BaseController;
import com.aidou.api.service.ReportService;
import com.aidou.api.vo.request.ReportUserRequest;
import com.aidou.api.vo.request.UserReportRequest;
import com.aidou.api.vo.respone.ReportUserItem;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by yingjiafeng on 2019/6/3.
 */
@Slf4j
@Api(tags = "举报")
@RestController
@RequestMapping("/check")
public class UserReportController extends BaseController {

    @Autowired
    private ReportService reportService;


    @ApiOperation(value = "举报用户", notes = "举报用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reportUid", value = "举报的用户ID", required = true),
            @ApiImplicitParam(name = "remarks", value = "备注", required = false),
            @ApiImplicitParam(name = "photo", value = "相册（数组）", required = false)
    }
    )
    @PostMapping(value = "/report/user")
    public AidouResult userReport(@Valid @RequestBody ReportUserRequest reportUserRequest, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                return AidouResult.error(bindingResult.getFieldError().getDefaultMessage());
            }
            reportService.reportUser(reportUserRequest);
            return AidouResult.success();
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }

    @ApiOperation(value = "用户审核", notes = "用户审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "审核ID", required = true),
            @ApiImplicitParam(name = "type", value = "忽略:2 封号:1 警告:0", required = true),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true)
    }
    )
    @PostMapping(value = "/user/check")
    public AidouResult check(@RequestBody UserReportRequest userReportRequest){
        try {
            reportService.reportCheck(userReportRequest);
            return AidouResult.success();
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }










    @ApiOperation(value = "用户举报列表", notes = "用户举报列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true),
            @ApiImplicitParam(name = "status", value = "1:审核  2:未审核", required = true),
    })
    @PostMapping(value = "/user/report/list/{page}/{status}")
    public AidouResult userSchoolList(@PathVariable Integer page,@PathVariable Integer status){
        try {
            List<ReportUserItem> reportUserList = reportService.findReportUserList(page, 10, status);
            return AidouResult.success(reportUserList);
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }


}
