package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/13 0:46
 */

/**
 * 订单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`order`")
public class Order {
    /**
     * 订单ID
     */
    @TableId(value = "ORDER_ID", type = IdType.ASSIGN_UUID)
    private String orderId;

    /**
     * 套餐ID
     */
    @TableField(value = "USER_ID")
    private String userId;

    /**
     * 交易创建用户ID
     */
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 交易单号
     */
    @TableField(value = "TRADE_ID")
    private String tradeId;

    /**
     * 实际支付金额
     */
    @TableField(value = "AMOUNT")
    private String amount;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;


    /**
     * 更新操作用户
     */
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

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
    @TableField(value = "PARAM4")
    private String param4;

    /**
     * 预留字段
     */
    @TableField(value = "APPOINT")
    private Date appoint;


    /**
     * 预留字段
     */
    @TableField(exist = false)
    private List<String> orderList;
}