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
    @TableId(value = "ID", type = IdType.INPUT)
    private String id;

    /**
     * 评论内容
     */
    @TableField(value = "CONTENT")
    private String content;

    /**
     * 创建者
     */
    @TableField(value = "CREATE_BY")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "CREATR_TIME")
    private Date creatrTime;

    /**
     * 评分
     */
    @TableField(value = "REATING")
    private Integer reating;

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
}