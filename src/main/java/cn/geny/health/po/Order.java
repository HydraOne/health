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
    @TableField(value = "CHECK_ID")
    private String checkId;

    /**
     * 用户ID
     */
    @TableField(value = "USER_ID")
    private String userId;

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
    @TableField(value = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME")
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
}