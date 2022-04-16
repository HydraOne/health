package cn.geny.health.service.impl;

import cn.geny.health.constant.CheckType;
import cn.geny.health.mapper.CheckEntityMapper;
import cn.geny.health.po.CheckCheck;
import cn.geny.health.po.CheckEntity;
import cn.geny.health.utils.UUIDUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
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

    @Autowired
    FileService fileService;



    @Override
    public boolean save(CheckEntity checkEntity) {
        String uuid = UUIDUtil.generateUUID();
        List<CheckCheck> list = new ArrayList<>();
        checkEntity.setId(uuid);
        CheckType type = EnumUtils.getEnum(CheckType.class,checkEntity.getType());
        if (!type.equals(CheckType.Item)){
            List<String> children = checkEntity.getChildren();
            children.forEach(itemId -> list.add(new CheckCheck(uuid, itemId)));
        }
        return super.save(checkEntity) && (list.size() == 0 || checkCheckService.saveBatch(list));
    }

    public boolean save(CheckEntity checkEntity, MultipartFile[] images) {
        String uuid = UUIDUtil.generateUUID();
        List<CheckCheck> list = new ArrayList<>();
        checkEntity.setId(uuid);
        CheckType type = EnumUtils.getEnum(CheckType.class,checkEntity.getType());

        List<String> uploadSuccessImgs = new ArrayList<>();
        Arrays.stream(images).forEach(image->{
            uploadSuccessImgs.add(fileService.uploadMedia(image).getId());
        });
        checkEntity.setParam1(String.join(",",uploadSuccessImgs));

        if (!type.equals(CheckType.Item)){
            List<String> children = checkEntity.getChildren();
            children.forEach(itemId -> list.add(new CheckCheck(uuid, itemId)));
        }
        return super.save(checkEntity) && (list.size() == 0 || checkCheckService.saveBatch(list));
    }

    public CheckEntity getCheckEntity(String id) {
        CheckEntity checkEntity = this.getById(id);
        List<CheckCheck> list = checkCheckService.list(new QueryWrapper<CheckCheck>().eq("check_id", id));
        if (Objects.nonNull(list)&&list.size()!=0) {
            List<String> listIds = list.stream().map(CheckCheck::getCheckCid).collect(Collectors.toList());
            List<CheckEntity> checkEntities = this.listByIds(listIds);
//            Map<String, List<CheckEntity>> map = checkEntities.stream().collect(Collectors.groupingBy(CheckEntity::getType));
//            checkEntity.setItems(map.get(CheckType.Item.name()));
//            checkEntity.setGroups(map.get(CheckType.Group.name()));
            Map<String, List<CheckEntity>> map = checkEntities.stream().collect(Collectors.groupingBy(CheckEntity::getType));
            List<CheckEntity> items = map.get(CheckType.Item.name());
            List<CheckEntity> groups = map.get(CheckType.Group.name());
            if (Objects.nonNull(items)){
                checkEntity.setItems(items.stream().map(CheckEntity::getId).collect(Collectors.toList()));
            }
            if (Objects.nonNull(groups)){
                checkEntity.setGroups(groups.stream().map(CheckEntity::getId).collect(Collectors.toList()));
            }
        }
        return checkEntity;
    }
}