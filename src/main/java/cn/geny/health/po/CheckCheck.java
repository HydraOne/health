package cn.geny.health.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/3/13 0:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "check_check")
public class CheckCheck {
    /**
     * 当前检查实体ID
     */
    @TableId(value = "CHECK_ID", type = IdType.INPUT)
    private String checkId;

    /**
     * 拥有的子ID
     */
    @TableId(value = "CHECK_CID", type = IdType.INPUT)
    private String checkCid;
}