package com.aidou.api.controller.web;

import com.aidou.api.service.WebArticleService;
import com.aidou.api.vo.respone.WebArticleRespone;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/9/20 11:47
 */

@Slf4j
@Api(tags = "网页推荐信息")
@RestController
@RequestMapping("/html")
public class BannderController {
    @Autowired
    private WebArticleService articleService;

    @ApiOperation(value = "推荐文件列表", notes = "推荐文件列表")
    @GetMapping(value = "/article")
    public AidouResult getUserCenter() {
        try {
            WebArticleRespone webArticleList = articleService.findWebArticleList();
            return AidouResult.success(0,"ok",webArticleList);
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }

}
