package cn.geny.health.service.impl;

import cn.geny.health.bo.User;
import cn.geny.health.common.RedisCache;
import cn.geny.health.constant.Constants;
import cn.geny.health.po.Account;
import cn.geny.health.utils.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

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

    public String login(String username, String password) {

//        boolean captchaOnOff = configService.selectCaptchaOnOff();
        boolean captchaOnOff = false;
//        // 验证码开关
//        if (captchaOnOff) {
//            validateCaptcha(username, code, uuid);
//        }
//        // 用户验证
//        Authentication authentication = null;
        Account account = userService.validateAccount(username, password);
        String token = UUIDUtil.generateUUID();
//        redisCache.setCacheObject(Constants.LOGIN_TOKEN_KEY + token, account.getId(), Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        redisCache.setCacheObject(Constants.LOGIN_TOKEN_KEY + token, account.getId());
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
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("account.password.not.match")));
//                throw new UserPasswordNotMatchException();
//            }
//            else
//            {
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
//                throw new ServiceException(e.getMessage());
//            }
//        }
//        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("account.login.success")));
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        recordLoginInfo(loginUser.getUserId());
//        // 生成token
        return token;
    }

    public User getUser(String token){
        String uid = redisCache.getCacheObject(Constants.LOGIN_TOKEN_KEY+token);
        Account account = userService.getById(uid);
        User user = null;
        if (!Objects.isNull(account)){
            user = new User();
            BeanUtils.copyProperties(account,user);
            user.setPhotoURL("http://192.168.117.130:19000/demo/" + user.getIcon());
        }
        return user;
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