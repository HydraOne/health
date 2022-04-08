package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.common.QueryProducer;
import cn.geny.health.model.QueryBody;
import cn.geny.health.po.Order;
import cn.geny.health.service.impl.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/13 1:00
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PutMapping("/put")
    public AjaxResult put(@RequestBody Order order) {
        if (orderService.save(order)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @GetMapping("/get")
    public AjaxResult get() {
        return AjaxResult.success(orderService.list());
    }

    @DeleteMapping("/del/{id}")
    public AjaxResult del(@PathVariable("id") String id) {
        if (orderService.removeById(id)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @PostMapping("/add")
    public AjaxResult add() {
        return null;
    }

    @GetMapping("/list")
    public AjaxResult list() {
        return null;
    }

    @GetMapping("/page")
    public AjaxResult page(@RequestBody QueryBody<Order> queryBody) {
        Page<Order> page = new Page<>(queryBody.getStart(), queryBody.getSize());
        QueryWrapper<Order> queryWrapper = QueryProducer.me().generatePageQuery(queryBody,Order.class);
        return AjaxResult.success(orderService.page(page, queryWrapper));
    }
}