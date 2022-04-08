package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.common.RedisCache;
import cn.geny.health.model.LoginBody;
import cn.geny.health.service.impl.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 验证码操作处理
 *
 * @author ruoyi
 */
@RestController
public class CaptchaController {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private CaptchaService captchaService;

    /**
     * 生成验证码
     */
    @GetMapping("/sendCaptchaCode/{token}")
    public AjaxResult getCode(@PathVariable("token") String token) throws IOException {
        String captchaCode = captchaService.captchaCodeSender(token);
        return AjaxResult.success();
    }

    /**
     * 校验验证码
     */
    @PostMapping("/checkCaptchaCode")
    public AjaxResult getCode(@RequestBody LoginBody loginBody){
        if (captchaService.checkCaptchaCode(loginBody.getToken(), loginBody.getCode())) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }
}