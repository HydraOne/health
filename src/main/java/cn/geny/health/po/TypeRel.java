package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/5/1 14:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "type_rel")
public class TypeRel {
    @TableField(value = "PID")
    private String pid;

    @TableField(value = "TID")
    private String tid;
}