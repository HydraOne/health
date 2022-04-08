package cn.geny.health;

import cn.geny.health.po.Account;
import cn.geny.health.service.impl.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class HealthApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        QueryWrapper<Account> query = new QueryWrapper<Account>();
        query.eq("STATUS", 0);
        System.out.println(query.getCustomSqlSegment());
        List<Account> accountList = userService.list();
        accountList.forEach(System.out::println);
    }

    @Test
    public void testSelectPage() {
        IPage<Account> page = new Page<>();
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("status", Arrays.asList(new Integer[]{1, 2, 57, 7}));
        System.out.println(userService.page(page, queryWrapper));
    }

    @Test
    public void demo1() {
        System.out.println(StringUtils.isNotBlank(null));
    }

    @Test
    public void demo2() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        Date tomorrow = DateUtils.addDays(today, 1);
        String tomorrowStr = dateFormat.format(tomorrow);
        System.out.println(DateUtils.parseDate(tomorrowStr+":00:00:00", "yyyy-MM-dd:HH:mm:ss"));
    }
}