package cn.geny.health.mapper;

import cn.geny.health.common.RedisCache;
import cn.geny.health.po.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/13 0:46
 */
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface RoleMapper extends BaseMapper<Role> {
}