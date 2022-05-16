package cn.geny.health.service.impl;

import cn.geny.health.bo.HealthProgramBO;
import cn.geny.health.mapper.HealthProgramMapper;
import cn.geny.health.po.HealthProgram;
import cn.geny.health.po.UserInfo;
import cn.geny.health.service.UserInfoService;
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
 * @date 2022/5/16 0:31
 */
@Service
public class HealthProgramService extends ServiceImpl<HealthProgramMapper, HealthProgram> {

    @Autowired
    private UserInfoService userInfoService;

    public List<HealthProgram> getProgramInfos(){
        List<HealthProgram> list = this.list(new QueryWrapper<HealthProgram>().orderByDesc("create_time"));
        Set<String> set = list.stream().map(HealthProgram::getPid).collect(Collectors.toSet());
        Map<String, UserInfo> infosMap = userInfoService.list(new QueryWrapper<UserInfo>().in("id", set))
                .stream().collect(Collectors.toMap(UserInfo::getId, Function.identity()));
        return list.stream().map(item -> {
            HealthProgramBO healthProgramBO = new HealthProgramBO();
            BeanUtils.copyProperties(item, healthProgramBO);
            healthProgramBO.setName(infosMap.get(item.getPid()).getName());
            return healthProgramBO;
        }).collect(Collectors.toList());
    }
}
