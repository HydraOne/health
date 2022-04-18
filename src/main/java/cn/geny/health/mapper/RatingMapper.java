package cn.geny.health.mapper;

import cn.geny.health.common.RedisCache;
import cn.geny.health.po.Rating;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/** 
 * TODO
 * @author wangjiahao
 * @date 2022/4/16 23:02
 */

@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface RatingMapper extends BaseMapper<Rating> {
}