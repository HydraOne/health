package cn.geny.health.service.impl;

import cn.geny.health.constant.AssociationType;
import cn.geny.health.mapper.OrderMapper;
import cn.geny.health.po.Association;
import cn.geny.health.po.CheckEntity;
import cn.geny.health.po.Order;
import cn.geny.health.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * TODO
 * @author wangjiahao
 * @date 2022/3/13 0:46
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {

    @Autowired
    private AssociationService associationService;

    @Autowired
    private CheckEntityService checkEntityService;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public boolean save(Order order) {
        List<String> orderList = order.getOrderList();
        super.save(order);
        if (Objects.nonNull(orderList)){
            List<Association> associationList = orderList.stream()
                    .map(item->new Association(order.getOrderId(),item, AssociationType.Order.name()))
                    .collect(Collectors.toList());
            associationService.saveBatch(associationList);
        }
        return true;
    }

    public Map<String,Object> getOrderInfo(String orderId){
        Order order = this.getById(orderId);
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("userInfo",userInfoService.getById(order.getUserId()));
        result.put("order",order);
        List<String> associationList = associationService.list(new QueryWrapper<Association>()
                .eq("id",orderId)
                .eq("type",AssociationType.Order.name()))
                .stream().map(Association::getCid).collect(Collectors.toList());
        result.put("checks",checkEntityService.list(new QueryWrapper<CheckEntity>().in("id",associationList)));
        return result;
    }
}
