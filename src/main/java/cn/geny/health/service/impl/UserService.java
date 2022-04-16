package cn.geny.health.service.impl;

import cn.geny.health.mapper.AccountMapper;
import cn.geny.health.po.Account;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/6 0:10
 */
@Service
public class UserService extends ServiceImpl<AccountMapper, Account> implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public Account getUserByUname(String uname) {
        return this.getOne(new QueryWrapper<Account>().eq("NAME", uname));
    }

    public Account validateAccount(String uname, String pwd) {
        int loginStatus = 0;
        Account currentAccount = getUserByUname(uname);
        if (currentAccount == null) {
            throw new RuntimeException("不存在账号");
        }
        if (!currentAccount.getPwd().equals(pwd)) {
            throw new RuntimeException("账号或密码错误");
        }
        return currentAccount;
    }

    public Boolean validateRegisterAccount(String uname){

        Account currentAccount = getUserByUname(uname);
        if (currentAccount!=null){
            throw new RuntimeException("账号以存在,无法注册");
        }
        return  true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Account user = this.getUserByUname(username);
        if (Objects.isNull(username))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new RuntimeException("登录用户：" + username + " 不存在");
        }
//        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
//        {
//            log.info("登录用户：{} 已被删除.", username);
//            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
//        }
//        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
//        {
//            log.info("登录用户：{} 已被停用.", username);
//            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
//        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(Account user)
    {
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User(user.getName(),user.getPwd(),auths);
    }
}
