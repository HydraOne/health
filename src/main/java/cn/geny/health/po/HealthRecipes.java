package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/16 11:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "health_recipes")
public class HealthRecipes {
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 执行人
     */
    @TableField(value = "PID")
    private String pid;

    /**
     * 执行时间
     */
    @TableField(value = "EXECUTE_TIME")
    private Date executeTime;

    /**
     * 功效说明
     */
    @TableField(value = "EFFECT_DESC")
    private String effectDesc;

    /**
     * 食谱内容
     */
    @TableField(value = "DETAILS")
    private String details;

    /**
     * 创建人
     */
    @TableField(value = "CREATE_BY",fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME",fill = FieldFill.INSERT)
    private Date createTime;
}