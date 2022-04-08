package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.common.QueryProducer;
import cn.geny.health.constant.CheckType;
import cn.geny.health.model.QueryBody;
import cn.geny.health.po.CheckEntity;
import cn.geny.health.service.impl.CheckCheckService;
import cn.geny.health.service.impl.CheckEntityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/13 0:51
 */
@RestController
@RequestMapping("/check")
public class CheckManageController {
    @Autowired
    private CheckEntityService checkEntityService;

    @Autowired
    private CheckCheckService checkCheckService;

    @PutMapping("/put")
    public AjaxResult putCheck(@RequestBody CheckEntity checkEntity) {
        if (checkEntityService.save(checkEntity)) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @PutMapping("/put/item")
    public AjaxResult putCheckItem(@RequestBody CheckEntity checkEntity) {
        checkEntity.setType(CheckType.Item.name());
        if (checkEntityService.save(checkEntity)) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @PutMapping("/put/group")
    public AjaxResult putCheckGroup(@RequestBody CheckEntity checkEntity) {
        checkEntity.setType(CheckType.Group.name());
        if (checkEntityService.save(checkEntity)) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @PutMapping("/put/plan")
    public AjaxResult putCheckPlan(@RequestBody CheckEntity checkEntity) {
        checkEntity.setType(CheckType.Plan.name());
        if (checkEntityService.save(checkEntity)) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @GetMapping("/get/{id}")
    public AjaxResult getItem(@PathVariable("id") String id) {
        return AjaxResult.success(checkEntityService.getCheckEntity(id));
    }


    @DeleteMapping("/del")
    public AjaxResult del() {
        return null;
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
    public AjaxResult page(@RequestBody QueryBody<CheckEntity> queryBody) {
        Page<CheckEntity> page = new Page<>(queryBody.getStart(), queryBody.getSize());
        QueryWrapper<CheckEntity> queryWrapper = QueryProducer.me().generatePageQuery(queryBody, CheckEntity.class);
        return AjaxResult.success(checkEntityService.page(page, queryWrapper));
    }
}