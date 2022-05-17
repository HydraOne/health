package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.service.impl.HealthTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/16 10:59
 */
@RestController
@RequestMapping("/healthTrend")
public class HealthTrendController {
    @Autowired
    private HealthTrendService healthTrendService;

    @GetMapping("/getList")
    public AjaxResult getList(){
        return AjaxResult.success().put("healthTrends",healthTrendService.getHealthTrends());
    }

//    @PutMapping("/add")
//    public AjaxResult addInfo(@RequestBody HealthRecipes healthRecipes){
//        return AjaxResult.success().put("healthRecipes",healthRecipesService.save(healthRecipes));
//    }
}
