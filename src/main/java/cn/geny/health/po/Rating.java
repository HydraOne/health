package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/4/16 23:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "rating")
public class Rating {
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 评论内容
     */
    @TableField(value = "COMMENT")
    private String comment;

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
     * 评分
     */
    @TableField(value = "REATING")
    private Integer rating;

    /**
     * 状态
     */
    @TableField(value = "`STATUS`")
    private String status;

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
    @TableField(value = "PRAM4")
    private String pram4;

    /**
     * 预留字段
     */
    @TableField(value = "PID")
    private String pid;

    /**
     * 预留字段
     */
    @TableField(exist = false)
    private String avatarUrl;

    /**
     * 预留字段
     */
    @TableField(exist = false)
    private Boolean isPurchased;

    /**
     * 预留字段
     */
    @TableField(exist = false)
    private Integer helpful;
}