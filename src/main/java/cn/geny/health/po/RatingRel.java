package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/4/16 23:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "rating_rel")
public class RatingRel {
    /**
     * 所属内容ID
     */
    @TableField(value = "PID")
    private String pid;

    /**
     * 评分ID
     */
    @TableField(value = "RID")
    private String rid;

    /**
     * 预留字段
     */
    @TableField(value = "`TYPE`")
    private String type;
}