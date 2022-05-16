package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.po.HealthInfo;
import cn.geny.health.service.impl.HealthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/15 16:32
 */
@RestController
@RequestMapping("/healthInfo")
public class HealthInfoController {
    @Autowired
    private HealthInfoService healthInfoService;

    @GetMapping("/getList")
    public AjaxResult getList(){
        return AjaxResult.success().put("healthInfos",healthInfoService.getHealthInfos());
    }

    @PutMapping("/add")
    public AjaxResult addInfo(@RequestBody HealthInfo healthInfo){
        return AjaxResult.success().put("healthInfos",healthInfoService.save(healthInfo));
    }
}
