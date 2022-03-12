package cn.geny.health.service.impl;

import cn.geny.health.mapper.UserMapper;
import cn.geny.health.po.User;
import cn.geny.health.utils.UUIDUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/6 0:10
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Override
    public boolean save(User entity) {
        entity.setId(UUIDUtil.GenerateUUID());
        return super.save(entity);
    }

    public User getUserByUname(String uname) {
        return this.getOne(new QueryWrapper<User>().eq("NAME", uname));
    }

    public User validateAccount(String uname, String pwd) {
        int loginStatus = 0;
        User currentUser = getUserByUname(uname);
        if (currentUser == null) {
            throw new RuntimeException("不存在账号");
        }
        if (!currentUser.getPwd().equals(pwd)) {
            throw new RuntimeException("账号或密码错误");
        }
        return currentUser;
    }
}