package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.po.Type;
import cn.geny.health.service.impl.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/1 14:51
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private TypeService typeService;

    @PutMapping("/put")
    public AjaxResult putElement(@RequestBody Type type){
        return AjaxResult.success(typeService.save(type));
    }
}
