package cn.geny.health.bo;

import cn.geny.health.po.HealthInfo;
import cn.geny.health.po.UserInfo;
import lombok.Data;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/15 17:15
 */
@Data
public class HealthInfoBO extends HealthInfo {
    private UserInfo userInfo;
}
