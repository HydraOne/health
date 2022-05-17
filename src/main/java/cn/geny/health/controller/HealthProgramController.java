package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.po.HealthProgram;
import cn.geny.health.service.impl.HealthProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/16 0:33
 */
@RestController
@RequestMapping("/healthProgram")
public class HealthProgramController {
    @Autowired
    private HealthProgramService healthProgramService;

    @GetMapping("/getList")
    public AjaxResult getList(){
        return AjaxResult.success().put("healthPrograms",healthProgramService.getProgramInfos());
    }

    @PutMapping("/add")
    public AjaxResult addInfo(@RequestBody HealthProgram healthProgram){
        return AjaxResult.success().put("healthProgram",healthProgramService.saveOrUpdate(healthProgram));
    }

    @GetMapping("/get/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id){
        return AjaxResult.success().put("healthProgram",healthProgramService.getHealthProgram(id));
    }
}