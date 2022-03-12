package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class UserRole {
    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.INPUT)
    private String userId;

    /**
     * 角色ID
     */
    @TableId(value = "ROLE_ID", type = IdType.INPUT)
    private String roleId;
}