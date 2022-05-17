package cn.geny.health.config;

import cn.geny.health.po.Account;
import cn.geny.health.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InsertOrUpdateDateHandler implements MetaObjectHandler {

    //插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        Account currentUser = SecurityUtils.getLoginUser();
        this.setFieldValByName("createBy", currentUser.getId(),metaObject);
        this.setFieldValByName("updateBy", currentUser.getId(),metaObject);
    }

    //修改时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        Account currentUser = SecurityUtils.getLoginUser();
        this.setFieldValByName("createBy", currentUser.getId(),metaObject);
        this.setFieldValByName("updateBy", currentUser.getId(),metaObject);
    }

}