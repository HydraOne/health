package cn.geny.health.utils;

import cn.geny.health.bo.User;
import cn.geny.health.constant.Constants;
import cn.geny.health.po.Account;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

/**
 * 安全服务工具类
 *
 * @author ruoyi
 */
public class SecurityUtils {
    /**
     * 用户ID
     **/
    public static String getUserId() {
        try {
            return getLoginUser().getId();
        } catch (Exception e) {
            throw new RuntimeException("获取用户ID异常" + HttpStatus.UNAUTHORIZED);
        }
    }

//    /**
//     * 获取部门ID
//     **/
//    public static Long getDeptId()
//    {
//        try
//        {
//            return getLoginUser().getDeptId();
//        }
//        catch (Exception e)
//        {
//            throw new RuntimeException("获取部门ID异常 " + HttpStatus.UNAUTHORIZED);
//        }
//    }

    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getName();
        } catch (Exception e) {
            throw new RuntimeException("获取用户账户异常" + HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static Account getLoginUser() {
        try {
            ;
//            return (LoginUser) ;
            return (Account) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息异常" + HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static User convertAccountToUser(Account account){
        User user = null;
        if (!Objects.isNull(account)){
            user = new User();
            BeanUtils.copyProperties(account,user);
            user.setPhotoURL(Constants.MINIO_URI + "/demo/" + user.getIcon());
        }
        return user;
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}