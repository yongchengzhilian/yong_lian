package com.aidou.controller;

import com.aidou.service.MatchmakerService;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.tool.ExceptionUtil;
import com.aidou.util.tool.GsonUtil;
import com.aidou.vo.rm.UserInfoEditDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = "红娘")
@RestController
@RequestMapping("/matchmaker")
public class MatchmakerController extends BaseController {

    @Autowired
    private MatchmakerService matchmakerService;


    @ApiOperation(value = "获取当前红娘介绍的用户列表")
    @GetMapping("/findUserList")
    public AidouResult findUserByMatchmaker() {
        AidouResult userList = matchmakerService.findUserList();
        System.out.println(GsonUtil.gsonString(userList));
        return   userList;
    }

    @ApiOperation(value = "添加用户  & 编辑  传uid 代表需要编辑")
    @PostMapping("/user/add")
    public AidouResult addUserInit(@Valid @RequestBody UserInfoEditDto userInfoEditDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return AidouResult.error(bindingResult.getFieldError().getDefaultMessage());
            }
            return matchmakerService.AddUser(userInfoEditDto);

        } catch (Exception e) {
            e.printStackTrace();
            return AidouResult.error(ExceptionUtil.getExceptionAllinformation_01(e));
        }
    }


    @ApiOperation(value = "关闭用户有些用户可能已经脱单了需要红娘主动去更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户ID", required = true),
            @ApiImplicitParam(name = "type", value = "类型(1 关闭  0 打开)", required = true)
    })
    @PostMapping("/user/status/{uid}/{type}")
    public AidouResult stopUserInit(@PathVariable Long uid, @PathVariable Integer type) {
        try {
            return matchmakerService.stopUser(uid, type);

        } catch (Exception e) {
            e.printStackTrace();
            return AidouResult.error(ExceptionUtil.getExceptionAllinformation_01(e));
        }

    }





    @ApiOperation(value = "用户详情")
    @PostMapping("/user/descript/{id}")
    public AidouResult addUserInit(@PathVariable Long id) {
        try {

            return matchmakerService.findUserDescriptById(id);

        } catch (Exception e) {
            e.printStackTrace();
            return AidouResult.error(ExceptionUtil.getExceptionAllinformation_01(e));
        }
    }



}
