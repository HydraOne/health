package cn.geny.health.controller;

import cn.geny.health.common.AjaxResult;
import cn.geny.health.common.QueryProducer;
import cn.geny.health.constant.CheckType;
import cn.geny.health.model.QueryBody;
import cn.geny.health.po.CheckEntity;
import cn.geny.health.service.impl.CheckCheckService;
import cn.geny.health.service.impl.CheckEntityService;
import cn.geny.health.service.impl.FileService;
import cn.geny.health.service.impl.RatingService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private FileService fileService;

    @Autowired
    private RatingService ratingService;

    @PutMapping("/put")
    public AjaxResult putCheck(@RequestParam("checkEntity") String checkEntityStr, MultipartFile[] images) {
        CheckEntity checkEntity = JSONObject.parseObject(checkEntityStr, CheckEntity.class);


        if (EnumUtils.isValidEnum(CheckType.class, checkEntity.getType())) {
            if (checkEntityService.save(checkEntity,images)) {
                return AjaxResult.success();
            }
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
        return AjaxResult.success()
                .put("product",checkEntityService.getCheckEntity(id));
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
        return AjaxResult.success(checkEntityService.list());
    }

    @GetMapping("/page")
    public AjaxResult page(@RequestBody QueryBody<CheckEntity> queryBody) {
        Page<CheckEntity> page = new Page<>(queryBody.getStart(), queryBody.getSize());
        QueryWrapper<CheckEntity> queryWrapper = QueryProducer.me().generatePageQuery(queryBody, CheckEntity.class);
        return AjaxResult.success(checkEntityService.page(page, queryWrapper));
    }

    @GetMapping("/review/page")
    public AjaxResult reviewPage(String productId,int pageNum,int pageSize){
        return AjaxResult.success().put("rating",ratingService.getRatingsPage(productId,pageNum,pageSize));
    }

}