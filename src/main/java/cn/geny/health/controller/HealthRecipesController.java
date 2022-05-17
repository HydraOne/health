package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.model.QueryBody;
import cn.geny.health.po.HealthRecipes;
import cn.geny.health.service.impl.HealthRecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/16 10:59
 */
@RestController
@RequestMapping("/healthRecipes")
public class HealthRecipesController {
    @Autowired
    private HealthRecipesService healthRecipesService;

    @GetMapping("/getList")
    public AjaxResult getList(){
        return AjaxResult.success().put("healthRecipes",healthRecipesService.getHealthRecipes());
    }

    @PutMapping("/add")
    public AjaxResult addInfo(@RequestBody HealthRecipes healthRecipes){
        return AjaxResult.success().put("healthRecipes",healthRecipesService.save(healthRecipes));
    }

    @GetMapping("/get/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id){
        return AjaxResult.success().put("healthRecipes",healthRecipesService.getHealthRecipe(id));
    }

    @PostMapping("/page")
    public AjaxResult getIPage(@RequestBody QueryBody<HealthRecipes> queryBody){
        return AjaxResult.success().put("healthRecipes",healthRecipesService.getPages(queryBody));
    }
}
