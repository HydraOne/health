package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/5/15 16:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "health_info")
public class HealthInfo {
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 健康信息归属人
     */
    @TableField(value = "PID")
    private String pid;

    /**
     * 详情信息
     */
    @TableField(value = "DETAILS")
    private String details;

    /**
     * 备注
     */
    @TableField(value = "REMARKS")
    private String remarks;

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