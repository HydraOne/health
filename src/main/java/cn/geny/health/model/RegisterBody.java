package cn.geny.health.model;

import lombok.Data;

@Data
public class RegisterBody {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 电子邮箱
     */
    private String email;
}
