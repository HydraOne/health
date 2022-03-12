package cn.geny.health.service.impl;

import cn.geny.health.common.RedisCache;
import cn.geny.health.constant.Constants;
import cn.geny.health.utils.CaptchaUtil;
import cn.geny.health.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/7 9:26
 */
@Component
public class CaptchaService {
    @Autowired
    RedisCache redisCache;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    UserService userService;

    public String[] captchaCodeBindUUID() {
        String uuid = UUIDUtil.GenerateUUID();
        String captchaCodeBindUUIDStr = Constants.CAPTCHA_CODE_KEY + uuid;
        String captchaCode = CaptchaUtil.generateCaptchaCode();
        redisCache.setCacheObject(captchaCodeBindUUIDStr, captchaCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        return new String[]{uuid, captchaCode};
    }

    public String captchaCodeSender(String email) {
        String[] captchaCodeBindUUIDs = captchaCodeBindUUID();
        new Thread(() -> {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setText(captchaCodeBindUUIDs[1]);
            simpleMailMessage.setFrom("hydraone123@qq.com");
            simpleMailMessage.setTo(email);
            javaMailSender.send(simpleMailMessage);
        }).start();
        return captchaCodeBindUUIDs[0];
    }
}