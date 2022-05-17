package cn.geny.health.service.impl;

import cn.geny.health.mapper.HealthTrendMapper;
import cn.geny.health.po.HealthTrend;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 * @author wangjiahao
 * @date 2022/5/17 14:22
 */
@Service
public class HealthTrendService extends ServiceImpl<HealthTrendMapper, HealthTrend> {

    public List<HealthTrend> getHealthTrends(){
        return this.list().subList(0,7);
    }

}
