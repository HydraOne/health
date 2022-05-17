package cn.geny.health.bo;

import cn.geny.health.po.HealthProgram;
import cn.geny.health.po.UserInfo;
import lombok.Data;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/5/16 0:31
 */
@Data
public class HealthProgramBO extends HealthProgram {
    private UserInfo userInfo;
}