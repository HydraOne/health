package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/5/1 14:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`type`")
public class Type {
    /**
     * ID主键
     */
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 名字
     */
    @TableField(value = "`NAME`")
    private String name;

    /**
     * 描述信息
     */
    @TableField(value = "DESCRIPTION")
    private String description;

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
     * 预留参数1
     */
    @TableField(value = "PARAM1")
    private String param1;

    /**
     * 预留参数2
     */
    @TableField(value = "PARAM2")
    private String param2;

    /**
     * 预留参数3
     */
    @TableField(value = "PARAM3")
    private String param3;
}