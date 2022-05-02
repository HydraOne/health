package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.service.impl.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/1 15:05
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/get")
    public AjaxResult getTypes() {
        return AjaxResult.success().put("type", typeService.list());
    }
}
