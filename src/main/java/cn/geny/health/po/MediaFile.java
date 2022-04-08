package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/4/3 23:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`file`")
public class MediaFile {
    /**
     * 文件主键
     */
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 文件名
     */
    @TableField(value = "`NAME`")
    private String name;

    /**
     * 创建者
     */
    @TableField(value = "CREATE_BY")
    private String createBy;

    /**
     * 创建日期
     */
    @TableField(value = "CREATE_DATE",fill = FieldFill.INSERT)
    private Date createDate;

    /**
     * 状态
     */
    @TableField(value = "`STATUS`")
    private String status;
}