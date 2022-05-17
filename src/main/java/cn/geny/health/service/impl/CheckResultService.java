package cn.geny.health.service.impl;

import cn.geny.health.bo.HealthResultsBO;
import cn.geny.health.constant.OrderStatus;
import cn.geny.health.mapper.CheckResultMapper;
import cn.geny.health.po.CheckEntity;
import cn.geny.health.po.CheckResult;
import cn.geny.health.po.Order;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
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

    @SuppressWarnings("unchecked")
    public Map<String, Object> getCheckResultByOrderId(String id) {
        Map<String, Object> orderInfo = orderService.getOrderInfo(id);
        Set<CheckEntity> checks= (Set<CheckEntity>) orderInfo.remove("checks");
        Map<String, CheckEntity> idCheckEntityMap = checks.stream().collect(Collectors.toMap(CheckEntity::getId, Function.identity()));
        List<CheckResult> checkResults = this.list(new QueryWrapper<CheckResult>().in("check_id", idCheckEntityMap.keySet()).eq("order_id", id));
        Map<String, CheckResult> idCheckResultMap = checkResults.stream().collect(Collectors.toMap(CheckResult::getCheckId, Function.identity()));
        List<JSONObject> jsonObjects = new ArrayList<>();
        idCheckEntityMap.forEach((key,checkEntity)->{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",checkEntity.getName());
            CheckResult checkResult = idCheckResultMap.get(key);
            jsonObject.put("content",checkResult.getContent());
            jsonObject.put("info",checkResult.getInfo());
            jsonObjects.add(jsonObject);
        });
        orderInfo.put("results",jsonObjects);
        return orderInfo;
    }

}