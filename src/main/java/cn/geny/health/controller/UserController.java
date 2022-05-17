package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.common.QueryProducer;
import cn.geny.health.model.QueryBody;
import cn.geny.health.po.Account;
import cn.geny.health.service.impl.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/6 0:33
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/current/{id}")
    public AjaxResult getCurrentUser(@PathVariable("id") String id) {
        return AjaxResult.success(userService.getById(id));
    }

    @GetMapping("/list")
    public AjaxResult getAllUsers() {
        return AjaxResult.success(userService.list());
    }

    @GetMapping("/page")
    public AjaxResult getPageUsers(@RequestBody QueryBody<Account> queryBody) {
        Page<Account> page = new Page<>(queryBody.getPageNum(), queryBody.getPageSize());
        QueryWrapper<Account> queryWrapper = QueryProducer.me().generatePageQuery(queryBody, Account.class);
        return AjaxResult.success(userService.page(page, queryWrapper));
    }

    @DeleteMapping("/delete")
    public AjaxResult getDeleteUsers(@RequestBody QueryBody<Account> queryBody) {
        Page<Account> page = new Page<>(queryBody.getPageNum(), queryBody.getPageSize());
        QueryWrapper<Account> queryWrapper = QueryProducer.me().generatePageQuery(queryBody, Account.class);
        return AjaxResult.success(userService.page(page, queryWrapper));
    }

    @PostMapping("/update")
    public AjaxResult updateUserInfo(@RequestBody Map params) {
        String photoURL = (String) params.get("photoURL");
        String name = (String) params.get("path");
        String preview = (String) params.get("preview");
        return null;
    }

    @GetMapping("/getCurrentUserRole")
    public AjaxResult getCurrentUserRole() {
        return AjaxResult.success().put("role",userService.getCurrentUserRole());
    }

}