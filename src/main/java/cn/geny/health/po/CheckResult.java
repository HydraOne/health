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
 * @date 2022/5/12 16:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "check_result")
public class CheckResult {
    /**
     * 订单ID
     */
    @TableField(value = "ORDER_ID")
    private String orderId;

    /**
     * 体检项目ID
     */
    @TableField(value = "CHECK_ID")
    private String checkId;

    /**
     * 结果内容
     */
    @TableField(value = "CONTENT")
    private String content;

    /**
     * 详细信息
     */
    @TableField(value = "INFO")
    private String info;

    /**
     * 结果填入人
     */
    @TableField(value = "CREATE_BY",fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 填入日期
     */
    @TableField(value = "CREATE_TIME",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 结果更新者
     */
    @TableField(value = "UPDATE_BY",fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 结果更新时间
     */
    @TableField(value = "UPDATE_TIME",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 状态
     */
    @TableField(value = "`STATUS`")
    private String status;
}