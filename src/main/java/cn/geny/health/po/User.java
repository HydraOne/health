package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/6 0:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`user`")
public class User {
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
     * 密码
     */
    @TableField(value = "PWD")
    private String pwd;

    /**
     * 用户头像
     */
    @TableField(value = "ICON")
    private String icon;

    /**
     * 联系方式
     */
    @TableField(value = "CONTACT")
    private String contact;

    /**
     * 账号状态
     */
    @TableField(value = "`STATUS`")
    private Integer status;
}