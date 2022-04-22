package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.common.RedisCache;
import cn.geny.health.model.LoginBody;
import cn.geny.health.model.RegisterBody;
import cn.geny.health.service.impl.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * 生成验证码(注册时)
     */
    @PostMapping("/sendCaptchaCode/register")
    public AjaxResult getRegisterCode(@RequestBody RegisterBody registerBody, HttpSession httpSession) throws IOException {

        //此部分应在前端验证，后期需要进行修改
        if(registerBody.getEmail().isEmpty()) {//判断邮件是否为NULL或空字符串
            throw new RuntimeException("输入电子邮箱不能为空");
        }
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(registerBody.getEmail());
        if (!matcher.matches()) {
            throw new RuntimeException("输入的电子邮箱格式不正确");
        }
        String captchaCode = captchaService.captchaCodeSenderToEmail(registerBody.getEmail());
        httpSession.setAttribute("captchaCode", captchaCode);
        httpSession.setMaxInactiveInterval(60*5);//设置验证码有效时间
        return AjaxResult.success("验证码发送成功",null);
    }

    /**
     * 校验验证码(注册时)
     */
    @PostMapping("/checkCaptchaCode/register")
    public AjaxResult CheckRegisterCode(@RequestBody RegisterBody registerBody,HttpServletRequest request){
        HttpSession session = request.getSession();
        String captchaCode= String.valueOf(session.getAttribute("captchaCode"));
        if (captchaCode==null) {
            throw new RuntimeException("验证码已超时，请重新验证");
        }
        if(!captchaCode.equals(registerBody.getCaptchaCode())){
            throw new RuntimeException("验证码输入错误，请输入正确验证码");
        }
        return AjaxResult.success();
    }
}
