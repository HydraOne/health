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
    * 检查实体
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "check_entity")
public class CheckEntity {
    /**
     * 检查实体ID
     */
    @TableId(value = "CHECK_ID", type = IdType.INPUT)
    private String checkId;

    /**
     * 名称
     */
    @TableField(value = "`NAME`")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "DESCRIPTION")
    private String description;

    /**
     * 类型
     */
    @TableField(value = "`TYPE`")
    private String type;

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
     * 删除标志
     */
    @TableField(value = "DEL_FLAG")
    private String delFlag;

    /**
     * 预留字段
     */
    @TableField(value = "PARAM1")
    private String param1;

    /**
     * 预留字段
     */
    @TableField(value = "PARAM2")
    private String param2;

    /**
     * 预留字段
     */
    @TableField(value = "PARAM3")
    private String param3;

    /**
     * 预留字段
     */
    @TableField(value = "PARAM4")
    private String param4;
}