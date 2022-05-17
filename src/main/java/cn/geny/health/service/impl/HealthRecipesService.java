package cn.geny.health.service.impl;

import cn.geny.health.bo.HealthRecipesBO;
import cn.geny.health.common.QueryProducer;
import cn.geny.health.mapper.HealthRecipesMapper;
import cn.geny.health.model.QueryBody;
import cn.geny.health.po.HealthRecipes;
import cn.geny.health.po.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
/** 
 * TODO
 * @author wangjiahao
 * @date 2022/5/16 10:53
 */
@Service
public class HealthRecipesService extends ServiceImpl<HealthRecipesMapper, HealthRecipes> {
    @Autowired
    private UserInfoService userInfoService;

    public List<HealthRecipes> getHealthRecipes(){
        List<HealthRecipes> list = this.list(new QueryWrapper<HealthRecipes>().orderByDesc("create_time"));
        Set<String> set = list.stream().map(HealthRecipes::getPid).collect(Collectors.toSet());
        Map<String, UserInfo> infosMap = list.size()!=0?userInfoService.list(new QueryWrapper<UserInfo>().in("id", set))
                .stream().collect(Collectors.toMap(UserInfo::getId, Function.identity())):new HashMap<>(8);
        return list.stream().map(item -> {
            HealthRecipesBO healthRecipesBO = new HealthRecipesBO();
            BeanUtils.copyProperties(item, healthRecipesBO);
            healthRecipesBO.setUserInfo(infosMap.get(item.getPid()));
            return healthRecipesBO;
        }).collect(Collectors.toList());
    }

    public HealthRecipesBO getHealthRecipe(String id){
        HealthRecipes healthInfo = this.getById(id);
        UserInfo userInfo = userInfoService.getById(healthInfo.getPid());
        HealthRecipesBO healthInfoBO = new HealthRecipesBO();
        BeanUtils.copyProperties(healthInfo, healthInfoBO);
        healthInfoBO.setUserInfo(userInfo);
        return healthInfoBO;
    }

    public List<HealthRecipes> getPages(QueryBody<HealthRecipes> queryBody){
        Page<HealthRecipes> page = new Page<>(queryBody.getPageNum(), queryBody.getPageSize());
        QueryWrapper<HealthRecipes> queryWrapper = QueryProducer.me().generatePageQuery(queryBody, HealthRecipes.class);
        return this.page(page,queryWrapper).getRecords();
    }
}
