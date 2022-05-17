package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/5/17 14:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "health_trend")
public class HealthTrend {
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private String id;

    /**
     * 归属人
     */
    @TableField(value = "PID")
    private String pid;

    /**
     * 健康指数
     */
    @TableField(value = "TREND_INDEX")
    private Integer trendIndex;

    /**
     * 记录时间
     */
    @TableField(value = "CREATE_TIME")
    private Date createTime;
}