package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/5/4 21:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_info")
public class UserInfo {
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 姓名
     */
    @TableField(value = "`NAME`")
    private String name;

    /**
     * 身份证
     */
    @TableField(value = "ID_CARD")
    private String idCard;

    /**
     * 出生日期
     */
    @TableField(value = "BIRTH")
    private Date birth;

    /**
     * 性别
     */
    @TableField(value = "GENDER")
    private String gender;

    /**
     * 创建者
     */
    @TableField(value = "CREATE_BY",fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "UPDATE_BY",fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 联系方式
     */
    @TableField(value = "CONTACT")
    private String contact;
}