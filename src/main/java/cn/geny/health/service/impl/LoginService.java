package cn.geny.health.service.impl;

import cn.geny.health.common.RedisCache;
import cn.geny.health.constant.Constants;
import cn.geny.health.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/6 15:57
 */
@Component
public class LoginService {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserService userService;

    public String login(String username, String password, String code, String uuid) {

//        boolean captchaOnOff = configService.selectCaptchaOnOff();
        boolean captchaOnOff = false;
//        // 验证码开关
        if (captchaOnOff) {
            validateCaptcha(username, code, uuid);
        }
//        // 用户验证
//        Authentication authentication = null;
        User user = userService.validateAccount(username, password);
//        try
//        {
//            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
//            authentication = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        }
//        catch (Exception e)
//        {
//            if (e instanceof BadCredentialsException)
//            {
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
//                throw new UserPasswordNotMatchException();
//            }
//            else
//            {
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
//                throw new ServiceException(e.getMessage());
//            }
//        }
//        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        recordLoginInfo(loginUser.getUserId());
//        // 生成token
        return user.getId();
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new RuntimeException("验证码已过期");
        }
        if (!code.equalsIgnoreCase(captcha)) {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new RuntimeException("验证码错误");
        }
    }
}