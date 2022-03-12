package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.common.RedisCache;
import cn.geny.health.service.impl.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/captchaCode/{email}")
    public AjaxResult getCode(@PathVariable("email") String email) throws IOException {
        AjaxResult ajax = AjaxResult.success();
        String uuid = captchaService.captchaCodeSender(email);
        ajax.put("uuid", uuid);
        return ajax;
    }
}