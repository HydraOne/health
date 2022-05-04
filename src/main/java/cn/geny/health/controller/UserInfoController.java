package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.po.UserInfo;
import cn.geny.health.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/4 22:00
 */
@RestController
@RequestMapping("userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/get")
    public AjaxResult getCurrentAllUserInfoByCreate(){
        return AjaxResult.success().put("usersInfo",userInfoService.list(new QueryWrapper<UserInfo>().orderByDesc("create_time")).subList(0,5));
    }

    @PutMapping("/put")
    public AjaxResult addUserInfo(@RequestBody UserInfo userInfo){
        userInfoService.save(userInfo);
        return AjaxResult.success();
    }

}
