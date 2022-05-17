package cn.geny.health.bo;

import cn.geny.health.po.HealthRecipes;
import cn.geny.health.po.UserInfo;
import lombok.Data;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/16 10:54
 */
@Data
public class HealthRecipesBO extends HealthRecipes {
    private UserInfo userInfo;
}
