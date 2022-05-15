package cn.geny.health.service.impl;

import cn.geny.health.mapper.AccountMapper;
import cn.geny.health.po.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RegisterService {
    @Autowired
    private UserService userService;

    @Autowired(required = false)
    private AccountMapper accountMapper;

    public int register(String userName,String email,String password){
        userService.validateRegisterAccount(userName);
        Account account=new Account();
        account.setName(userName);
        account.setEmail(email);
        account.setPwd(password);
        return accountMapper.insert(account);
    }
}