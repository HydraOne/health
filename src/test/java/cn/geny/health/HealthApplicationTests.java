package cn.geny.health;

import cn.geny.health.po.User;
import cn.geny.health.service.impl.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class HealthApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.eq("STATUS", 0);
        System.out.println(query.getCustomSqlSegment());
        List<User> userList = userService.list();
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectPage() {
        IPage<User> page = new Page<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("status", Arrays.asList(new Integer[]{1, 2, 57, 7}));
        System.out.println(userService.page(page, queryWrapper));
    }
}