package com.aidou.api.controller.user;

import com.aidou.api.service.*;
import com.aidou.api.vo.UserToken;
import com.aidou.api.vo.request.RedLineRequest;
import com.aidou.api.vo.request.UserInfoRequest;
import com.aidou.api.vo.request.UserRecommendListRequest;
import com.aidou.api.vo.request.UserSmallInfoRequest;
import com.aidou.api.vo.respone.UserCenterInfoRespone;
import com.aidou.api.vo.user.ReommendUserVo;
import com.aidou.api.vo.user.UserLikeVo;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.StatusCode;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.ExceptionUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yingjiafeng on 2019/5/15.
 */

@Slf4j
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserImageService userImageService;


    @Autowired
    private FriendApplicationService friendApplicationService;

    @Autowired
    private FriendRecommendService friendRecommendService;


    @ApiOperation(value = "用户列表", notes = "用户列表")
    @ApiResponse(code = StatusCode.OK, message = "用户推荐", response = ReommendUserVo.class)
    @PostMapping(value = "/searchlist")
    public AidouResult getRecommendUserList(@RequestBody UserRecommendListRequest  userRecommendListRequest) {
        try {
          return friendRecommendService.searchlist(userRecommendListRequest);
        }
        catch (BizException e){
            e.printStackTrace();
            return AidouResult.error(e.getLocalizedMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return AidouResult.error("获取数据失败");
        }
    }


    @ApiOperation(value = "发送红线", notes = "发送红线")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "被告白的用户ID", required = true),
            @ApiImplicitParam(name = "content", value = "表白", required = false)
    }
    )
    @PostMapping(value = "send/line")
    public AidouResult sendRedLine(@RequestBody RedLineRequest redLineRequest) {
        try {
            userService.sendRedLine(Long.valueOf(redLineRequest.getUid()), redLineRequest.getContent());
        } catch (BizException u) {
            return AidouResult.error(u.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return AidouResult.error("获取数据失败");
        }
        return AidouResult.success();
    }


    @ApiOperation(value = "互相喜欢", notes = "互相喜欢")
    @PostMapping(value = "find/like/each")
    public AidouResult findLikeEach() {
        try {
            List<UserLikeVo> likeEachUserList = friendApplicationService.getLikeEachUserList();
            return AidouResult.success(likeEachUserList);
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }


    @ApiOperation(value = "申请列表", notes = "申请列表")
    @PostMapping(value = "find/like")
    public AidouResult findLike() {
        try {
            List<UserLikeVo> likeEachUserList = friendApplicationService.getLikeAppleList();
            return AidouResult.success(likeEachUserList);
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }

    @ApiOperation(value = "牵线同意", notes = "牵线同意")
    @ApiImplicitParam(name = "id", value = "列表中的id", required = true)
    @PostMapping(value = "remove/like/agree/{id}/")
    public AidouResult agreeLike(@PathVariable Long id) {
        try {
            userService.agreeLike(id);
            return AidouResult.success();
        } catch (BizException e) {
            return AidouResult.error(e.getMessage());
        }
    }
    @ApiOperation(value = "牵线拒绝（牵手后拒绝）", notes = "牵线拒绝（牵手后拒绝）")
    @ApiImplicitParam(name = "id", value = "列表中的id", required = true)
    @PostMapping(value = "remove/like/refuse/{id}/")
    public AidouResult refuseLike(@PathVariable Long id) {
        try {
            userService.refuseLike(id);
            return AidouResult.success();
        } catch (BizException e) {
            return AidouResult.error(e.getMessage());
        }
    }

    @ApiOperation(value = "申请拒绝", notes = "申请拒绝")
    @ApiImplicitParam(name = "id", value = "列表中的id", required = true)
    @PostMapping(value = "remove/application/refuse/{id}/")
    public AidouResult applicationRefuse(@PathVariable Long id) {
        try {
            userService.applicationRefuse(id);
            return AidouResult.success();
        } catch (BizException e) {
            return AidouResult.error(e.getMessage());
        }
    }


    @ApiOperation(value = "获取当前用户资料（编辑修改）", notes = "获取当前用户资料（编辑修改）")
    @PostMapping(value = "selfinfo")
    public AidouResult currentUserinfo() {
        try {
            UserInfoRequest userInfoRequest = userService.findCurrentEditUserInfo();
            return AidouResult.success(userInfoRequest);
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }


    @ApiOperation(value = "个人中心", notes = "个人中心")
    @PostMapping(value = "center")
    public AidouResult getUserCenter() {
        try {
            UserCenterInfoRespone centerInfoRespone=userService.findUserCenterInfo();
            return AidouResult.success(centerInfoRespone);
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }


    @ApiOperation(value = "头像", notes = "头像")
    @PostMapping(value = "updatedUserSmallInfo")
    public AidouResult updatedUserSmallInfo(@RequestBody UserSmallInfoRequest userSmallInfoRequest) {
        userService.updatedUserSmallInfo(userSmallInfoRequest);
        return AidouResult.success();
    }



    @ApiOperation(value = "根据token获取用户状态信息", notes = "根据token获取用户状态信息")
    @PostMapping(value = "status")
    public AidouResult findUserStatus() {
        try {
            UserToken userStatus = userService.findCurrentUserStatus();
            return AidouResult.success(userStatus);
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除照片", notes = "删除照片")
    @GetMapping(value = "/photo/del/{fileSet}")
    public AidouResult deletePhoto(@PathVariable String  fileSet) {
        try {
            userImageService.deleteUserImgByPhoeoName(fileSet);
            return AidouResult.success();
        } catch (BizException e) {
            e.printStackTrace();
            return AidouResult.error(e.getMessage());
        }
    }

}
