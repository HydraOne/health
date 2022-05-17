package cn.geny.health.service.impl;

import cn.geny.health.mapper.UserInfoMapper;
import cn.geny.health.po.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 * @author wangjiahao
 * @date 2022/5/4 21:18
 */
@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> {


    public List<UserInfo> getUserByName(String name){
        Page<UserInfo> objectPage = new Page<>(0,10);
        return this.page(objectPage,new QueryWrapper<UserInfo>().like("name",name)).getRecords();
    }

}
