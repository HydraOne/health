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
 * @date 2022/5/15 14:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "message")
public class Message {
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private String id;

    /**
     * 接收人
     */
    @TableField(value = "TO_USER")
    private String toUser;

    /**
     * 发送人
     */
    @TableField(value = "CREATE_BY",fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 发送时间
     */
    @TableField(value = "CREATE_TIME",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 消息内容
     */
    @TableField(value = "MSG")
    private String msg;

    /**
     * 内容类型
     */
    @TableField(value = "CONTENT_TYPE")
    private String contentType;
}