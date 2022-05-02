package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/5/2 1:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "association")
public class Association {
    /**
     * 父级ID
     */
    @TableField(value = "ID")
    private String id;

    /**
     * 子级ID
     */
    @TableField(value = "CID")
    private String cid;

    /**
     * 关联类型
     */
    @TableField(value = "`TYPE`")
    private String type;
}