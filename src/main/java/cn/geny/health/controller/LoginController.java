package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.constant.Constants;
import cn.geny.health.model.LoginBody;
import cn.geny.health.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/6 15:52
 */
@RestController
@RequestMapping("/account")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
//        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
//                loginBody.getUuid());

        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        ajax.put(Constants.TOKEN, token);
        ajax.put("user", loginService.getUser(token));
        return ajax;
    }

    @GetMapping("/getInfo/{token}")
    public AjaxResult getInfo(@PathVariable("token") String token) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
//        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
//                loginBody.getUuid());

        ajax.put("user", loginService.getUser(token));
        return ajax;
    }
}