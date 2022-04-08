package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/3/13 0:46
 */
/**
    * 用户和角色关联表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_role")
public class AccountRole {
    /**
     * 用户ID
     */
    @TableField(value = "USER_ID")
    private String userId;

    /**
     * 角色ID
     */
    @TableField(value = "ROLE_ID")
    private String roleId;
}