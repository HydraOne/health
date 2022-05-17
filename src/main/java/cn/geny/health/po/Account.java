package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/17 12:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "account")
public class Account {
    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private String id;

    /**
     * 登录用户名
     */
    @TableField(value = "`NAME`")
    private String name;

    /**
     * 显示的用户名
     */
    @TableField(value = "DISPLAY_NAME")
    private String displayName;

    /**
     * 密码
     */
    @TableField(value = "PWD")
    @JsonIgnore
    private String pwd;

    /**
     * 用户头像
     */
    @TableField(value = "ICON")
    @JsonIgnore
    private String icon;

    /**
     * 联系方式
     */
    @TableField(value = "CONTACT")
    private String email;

    /**
     * 账号状态
     */
    @TableField(value = "`STATUS`")
    private Integer status;

    /**
     * 当前用户角色
     */
    @TableField(value = "`ROLE`")
    private String role;
}