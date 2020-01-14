package com.aidou.controller;

import com.aidou.service.RmUserService;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.tool.ExceptionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yingjiafeng on 2019/4/29.
 */

@Api(tags = "资料")
@RestController
@RequestMapping("/user")
public class UserController  extends BaseController {

    @Autowired
    private RmUserService rmUserService;




    @ApiOperation(value = "基本信息设置(新增  & 更新)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "area",value = "负责区域",required = true),
            @ApiImplicitParam(name = "nikename",value = "昵称"),
            @ApiImplicitParam(name = "wechat", value = "微信号", required = true)
    })
    @PostMapping("/create/baseinfo")
    public AidouResult  addBaseInfoSetting(String area,String nikename,String wechat){
      try {
          return rmUserService.createBasic(area,nikename,wechat);
      }catch (Exception e){
          return AidouResult.error(ExceptionUtil.getExceptionAllinformation_01(e));
      }
    }




    @ApiOperation(value = "获取信息")
    @GetMapping("/get/baseinfo")
    public AidouResult  getBaseInfoSetting(){
        try {
            return rmUserService.getBaseinfo();
        }catch (Exception e){
            return AidouResult.error(ExceptionUtil.getExceptionAllinformation_01(e));
        }
    }






}
