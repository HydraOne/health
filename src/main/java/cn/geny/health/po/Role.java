package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/3/13 0:46
 */
/**
    * 角色信息表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`role`")
public class Role {
    /**
     * 角色ID
     */
    @TableId(value = "ROLE_ID", type = IdType.INPUT)
    private String roleId;

    /**
     * 角色名称
     */
    @TableField(value = "ROLE_NAME")
    private String roleName;

    /**
     * 角色权限字符串
     */
    @TableField(value = "ROLE_KEY")
    private String roleKey;

    /**
     * 显示顺序
     */
    @TableField(value = "ROLE_SORT")
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @TableField(value = "DATA_SCOPE")
    private String dataScope;

    /**
     * 菜单树选择项是否关联显示
     */
    @TableField(value = "MENU_CHECK_STRICTLY")
    private Boolean menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示
     */
    @TableField(value = "DEPT_CHECK_STRICTLY")
    private Boolean deptCheckStrictly;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField(value = "`STATUS`")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField(value = "DEL_FLAG")
    private String delFlag;

    /**
     * 创建者
     */
    @TableField(value = "CREATE_BY")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "UPDATE_BY")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField(value = "REMARK")
    private String remark;
}