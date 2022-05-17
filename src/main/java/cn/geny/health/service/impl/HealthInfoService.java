package cn.geny.health.service.impl;

import cn.geny.health.bo.HealthInfoBO;
import cn.geny.health.mapper.HealthInfoMapper;
import cn.geny.health.po.HealthInfo;
import cn.geny.health.po.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO
 * @author wangjiahao
 * @date 2022/5/15 16:31
 */
@Service
public class HealthInfoService extends ServiceImpl<HealthInfoMapper, HealthInfo> {
    @Autowired
    private UserInfoService userInfoService;

    public List<HealthInfoBO> getHealthInfos(){
        List<HealthInfo> list = this.list(new QueryWrapper<HealthInfo>().orderByDesc("create_time"));
        Set<String> set = list.stream().map(HealthInfo::getPid).collect(Collectors.toSet());
        Map<String,UserInfo> infosMap = userInfoService.list(new QueryWrapper<UserInfo>().in("id", set))
                .stream().collect(Collectors.toMap(UserInfo::getId, Function.identity()));
        return list.stream().map(item -> {
            HealthInfoBO healthInfoBO = new HealthInfoBO();
            BeanUtils.copyProperties(item, healthInfoBO);
            healthInfoBO.setUserInfo(infosMap.get(item.getPid()));
            return healthInfoBO;
        }).collect(Collectors.toList());
    }

    public HealthInfoBO getHealthInfo(String id){
        HealthInfo healthInfo = this.getById(id);
        UserInfo userInfo = userInfoService.getById(healthInfo.getPid());
        HealthInfoBO healthInfoBO = new HealthInfoBO();
        BeanUtils.copyProperties(healthInfo, healthInfoBO);
        healthInfoBO.setUserInfo(userInfo);
        return healthInfoBO;
    }
}
