package cn.geny.health.controller;

import cn.geny.health.bo.HealthResultsBO;
import cn.geny.health.common.AjaxResult;
import cn.geny.health.service.CheckResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/12 16:20
 */
@RestController
@RequestMapping("/checkResult")
public class CheckResultController {

    @Autowired
    private CheckResultService checkResultService;

    @PutMapping("/put")
    public AjaxResult addHealthResult(@RequestBody HealthResultsBO healthResults) {
        if (checkResultService.saveCheckResult(healthResults)){
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }
}
