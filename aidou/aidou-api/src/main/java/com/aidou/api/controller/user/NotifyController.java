package com.aidou.api.controller.user;

import com.aidou.api.service.NotifyService;
import com.aidou.api.vo.respone.NotifyItemRespone;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/24 17:34
 */

@Slf4j
@Api(tags = "通知管理")
@RestController
@RequestMapping("/notify")
public class NotifyController {

    @Autowired
    private NotifyService notifyService;


    @ApiOperation(value = "通知列表", notes = "通知列表")
    @GetMapping(value = "/list")
    public AidouResult getUserCenter() {
        try {
            List<NotifyItemRespone> notifyList = notifyService.findNotifyList();
            return AidouResult.success(notifyList);
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }

    @ApiOperation(value = "通知列表", notes = "通知列表")
    @ApiImplicitParam(name = "mid", value = "通知ID", required = true)
    @GetMapping(value = "/delete/{mid}")
    public AidouResult deleteNotify(@PathVariable Long mid) {
        try {
            notifyService.deleteNotigyById(mid);
            return AidouResult.success();
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }



    @ApiOperation(value = "通知详情", notes = "通知详情")
    @ApiImplicitParam(name = "mid", value = "通知ID", required = true)
    @GetMapping(value = "/info/{mid}")
    public AidouResult  infoNotify(@PathVariable Long mid) {
        try {
            NotifyItemRespone notifyByMid = notifyService.getNotifyByMid(mid);
            return AidouResult.success(notifyByMid);
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }


}
