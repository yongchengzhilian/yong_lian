package com.aidou.api.controller.user;

import com.aidou.api.controller.BaseController;
import com.aidou.api.service.ShareService;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/14
 * Description:
 */

@Slf4j
@Api(tags = "分享")
@RestController
@RequestMapping("/share")
public class ShareUserController extends BaseController {

    @Autowired
    private ShareService shareService;


    @ApiOperation(value = "分享详情", notes = "分享详情")
    @PostMapping(value = "/info")
    public AidouResult shareInfo() {
        try {
            Map<String, Object> stringListMap = shareService.info();
            return AidouResult.success(stringListMap);
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return AidouResult.error("获取数据失败");
        }
    }


}
