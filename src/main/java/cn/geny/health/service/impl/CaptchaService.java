package cn.geny.health.service.impl;

import cn.geny.health.common.RedisCache;
import cn.geny.health.constant.Constants;
import cn.geny.health.manager.AsyncManager;
import cn.geny.health.manager.factory.AsyncFactory;
import cn.geny.health.po.Account;
import cn.geny.health.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;
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
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    UserService userService;

    public String captchaCodeBindToken(String token) {
        String captchaCodeBindUUIDStr = Constants.CAPTCHA_CODE_KEY + token;
        String captchaCode = CaptchaUtil.generateCaptchaCode();
        redisCache.setCacheObject(captchaCodeBindUUIDStr, captchaCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        return captchaCode;
    }

    public String captchaCodeSender(String token) {
        String userId = redisCache.getCacheObject(Constants.LOGIN_TOKEN_KEY + token);
        Account account = userService.getById(userId);
        String captchaCode = captchaCodeBindToken(token);
        AsyncManager.me().execute(AsyncFactory.sendTextEmail(account.getEmail(),captchaCode));
//        new Thread(() -> {
//            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//            simpleMailMessage.setText(captchaCode);
////            simpleMailMessage.setFrom("hydraone123@qq.com");
//            simpleMailMessage.setFrom("m72875509qiaonuo@163.com");
//            simpleMailMessage.setTo();
//            javaMailSender.send(simpleMailMessage);
//        }).start();
        return captchaCode;
    }

    public boolean checkCaptchaCode(String token, String captchaCode) {
        String bindCaptchaCode = redisCache.getCacheObject(Constants.CAPTCHA_CODE_KEY + token);
        if (bindCaptchaCode.equals(captchaCode)) {
            return true;
        }
        return false;
    }

    public  String captchaCodeSenderToEmail(String recevier){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(recevier);
        String  generateCaptchaCode= CaptchaUtil.generateCaptchaCode(6);
        simpleMailMessage.setText("??????????????????"+generateCaptchaCode+",???????????????5????????????????????????????????????????????????????????????");
        simpleMailMessage.setSubject("????????????---?????????");
        try {
            simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
            javaMailSender.send(simpleMailMessage);
        }catch (RuntimeException e){
            throw new RuntimeException("?????????????????????,???????????????");
        }
        return generateCaptchaCode;
    }

}
