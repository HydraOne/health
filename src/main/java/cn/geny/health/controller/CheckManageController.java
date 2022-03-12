package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.service.impl.CheckEntityService;
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

    @PutMapping("/put")
    public AjaxResult put() {
        return null;
    }

    @GetMapping("/get")
    public AjaxResult get() {
        return null;
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
    public AjaxResult page() {
        return null;
    }
}
