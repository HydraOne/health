package cn.geny.health.service.impl;

import cn.geny.health.constant.CheckType;
import cn.geny.health.mapper.CheckEntityMapper;
import cn.geny.health.po.CheckCheck;
import cn.geny.health.po.CheckEntity;
import cn.geny.health.utils.UUIDUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/13 0:46
 */
@Service
public class CheckEntityService extends ServiceImpl<CheckEntityMapper, CheckEntity> {

    @Autowired
    CheckCheckService checkCheckService;


    @Override
    public boolean save(CheckEntity checkEntity) {
        String uuid = UUIDUtil.generateUUID();
        List<CheckCheck> list = new ArrayList<>();
        checkEntity.setCheckId(uuid);
        String type = checkEntity.getType();
        if (CheckType.Plan.name().equals(type) || CheckType.Group.name().equals(type)) {
            List<CheckEntity> items = checkEntity.getItems();
            if (items != null) {
                items.forEach(childCheckEntity -> list.add(new CheckCheck(uuid, childCheckEntity.getCheckId())));
            }
            List<CheckEntity> groupsId = checkEntity.getGroups();
            if (CheckType.Plan.name().equals(type) && items != null) {
                groupsId.forEach(childCheckEntity -> list.add(new CheckCheck(uuid, childCheckEntity.getCheckId())));
            }
        }
        return super.save(checkEntity) && (list.size() == 0 || checkCheckService.saveBatch(list));
    }

    public CheckEntity getCheckEntity(String id) {
        CheckEntity checkEntity = this.getById(id);
        List<CheckCheck> list = checkCheckService.list(new QueryWrapper<CheckCheck>().eq("check_id", id));
        if (list != null) {
            List<String> listIds = list.stream().map(CheckCheck::getCheckCid).collect(Collectors.toList());
            List<CheckEntity> checkEntities = this.listByIds(listIds);
            Map<String, List<CheckEntity>> map = checkEntities.stream().collect(Collectors.groupingBy(CheckEntity::getType));
            checkEntity.setItems(map.get(CheckType.Item.name()));
            checkEntity.setGroups(map.get(CheckType.Group.name()));
        }
        return checkEntity;
    }
}