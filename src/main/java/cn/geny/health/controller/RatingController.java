package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.po.Rating;
import cn.geny.health.service.impl.CheckEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/4/17 17:45
 */
@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private CheckEntityService checkEntityService;

    @PostMapping("/add/{productId}")
    public AjaxResult addReview(@RequestBody Rating rating,@PathVariable("productId") String productId){
        if(checkEntityService.toCheckEntityAddReview(productId,rating)){
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

}
