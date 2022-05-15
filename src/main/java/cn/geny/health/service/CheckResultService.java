package cn.geny.health.service;

import cn.geny.health.bo.HealthResultsBO;
import cn.geny.health.constant.OrderStatus;
import cn.geny.health.mapper.CheckResultMapper;
import cn.geny.health.po.CheckResult;
import cn.geny.health.po.Order;
import cn.geny.health.service.impl.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/12 16:17
 */
@Service
public class CheckResultService extends ServiceImpl<CheckResultMapper, CheckResult> {
    @Autowired
    private OrderService orderService;

    public boolean saveCheckResult(HealthResultsBO healthResultsBO) {
        List<CheckResult> list = new ArrayList<>();
        String orderId = healthResultsBO.getOrderId();
        List<HealthResultsBO.HealthResult> items = healthResultsBO.getItems();
        if (Objects.nonNull(items) && items.size() > 0) {
            List<CheckResult> collect = items.stream().map(item -> {
                CheckResult checkResult = new CheckResult();
                checkResult.setOrderId(orderId);
                checkResult.setCheckId(item.getId());
                checkResult.setContent(item.getContent());
                checkResult.setInfo(item.getInfo());
                return checkResult;
            }).collect(Collectors.toList());
            list.addAll(collect);
        }
        Order order = new Order();
        order.setId(healthResultsBO.getOrderId());
        order.setStatus(OrderStatus.isRecord.name());
        orderService.saveOrUpdate(order);
        return this.saveBatch(list);
    }
}