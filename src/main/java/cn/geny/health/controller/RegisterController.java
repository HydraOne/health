package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;

import cn.geny.health.model.RegisterBody;
import cn.geny.health.service.impl.RegisterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public AjaxResult resgister(@RequestBody RegisterBody registerBody){
        AjaxResult ajaxResult = AjaxResult.success();
        if(registerService.register(registerBody.getUsername(),registerBody.getEmail(),registerBody.getPassword())>0){
            return   ajaxResult;
        }
        else {
            return AjaxResult.error("注册失败");
        }
    }

}
