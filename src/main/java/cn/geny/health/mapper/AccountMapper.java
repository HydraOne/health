package cn.geny.health.mapper;

import cn.geny.health.common.RedisCache;
import cn.geny.health.po.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/6 0:10
 */
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public interface AccountMapper extends BaseMapper<Account> {
}