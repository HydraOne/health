package cn.geny.health.service.impl;

import cn.geny.health.bo.Summary;
import cn.geny.health.constant.AssociationType;
import cn.geny.health.constant.CheckType;
import cn.geny.health.constant.Constants;
import cn.geny.health.mapper.CheckEntityMapper;
import cn.geny.health.po.*;
import cn.geny.health.service.AssociationService;
import cn.geny.health.service.TypeRelService;
import cn.geny.health.utils.SecurityUtils;
import cn.geny.health.utils.UUIDUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

    @Autowired
    RatingService ratingService;

    @Autowired
    RatingRelService ratingRelService;

    @Autowired
    TypeRelService typeRelService;

    @Autowired
    AssociationService associationService;


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
        CheckType type = EnumUtils.getEnum(CheckType.class,checkEntity.getType());

        List<Association> typeRels = new ArrayList<>();

        String description = checkEntity.getDescription();
        if (StringUtils.isNotBlank(description)){
            checkEntity.setDescription(description.replaceAll(Constants.MINIO_URI + "/demo/",""));
        }

        List<String> uploadSuccessImgs = new ArrayList<>();
        if (Objects.nonNull(images)){
            Arrays.stream(images).forEach(image->{
                uploadSuccessImgs.add(fileService.uploadMedia(image).getId());
            });
        }
        checkEntity.setParam1(String.join(",",uploadSuccessImgs));
        boolean saveIsSuccess = super.saveOrUpdate(checkEntity);

        associationService.remove(new QueryWrapper<Association>().eq("id",checkEntity.getId()).eq("type", AssociationType.Tag.name()));
        List<String> tags = checkEntity.getTags();
        if (Objects.nonNull(tags)){
            tags.forEach(itemId -> typeRels.add(new Association(checkEntity.getId(), itemId,AssociationType.Tag.name())));
        }

        associationService.remove(new QueryWrapper<Association>().eq("id",checkEntity.getId()).eq("type", AssociationType.Check.name()));
        List<String> children = checkEntity.getChildren();
        if (!type.equals(CheckType.Item)&&Objects.nonNull(children)){
            children.forEach(itemId -> typeRels.add(new Association(checkEntity.getId(), itemId,AssociationType.Check.name())));
        }

        associationService.saveBatch(typeRels);

        return saveIsSuccess;
    }

    public CheckEntity getCheckEntity(String id) {
        CheckEntity checkEntity = this.getById(id);
        if (Objects.isNull(checkEntity)){
            return null;
        }
        List<Association> typeRels = associationService.list(new QueryWrapper<Association>().eq("id", id).eq("type",AssociationType.Tag.name()));
        List<Association> checkRels = associationService.list(new QueryWrapper<Association>().eq("id", id).eq("type",AssociationType.Check.name()));
        Summary summary = ratingService.getSummary(id);
        checkEntity.setSummary(summary);
        List<String> checkRelIds = checkRels.stream().map(Association::getCid).collect(Collectors.toList());
        List<String> typeRelIds = typeRels.stream().map(Association::getCid).collect(Collectors.toList());
        checkEntity.setTags(typeRelIds);
        checkEntity.setChildren(checkRelIds);
//        if (Objects.nonNull(checkRels)&&checkRels.size()!=0) {
//
////            List<CheckEntity> checkEntities = this.listByIds(listIds);
//////            Map<String, List<CheckEntity>> map = checkEntities.stream().collect(Collectors.groupingBy(CheckEntity::getType));
//////            checkEntity.setItems(map.get(CheckType.Item.name()));
//////            checkEntity.setGroups(map.get(CheckType.Group.name()));
////            Map<String, List<CheckEntity>> map = checkEntities.stream().collect(Collectors.groupingBy(CheckEntity::getType));
////            List<CheckEntity> items = map.get(CheckType.Item.name());
////            List<CheckEntity> groups = map.get(CheckType.Group.name());
////            if (Objects.nonNull(items)){
////                checkEntity.setItems(items.stream().map(CheckEntity::getId).collect(Collectors.toList()));
////            }
////            if (Objects.nonNull(groups)){
////                checkEntity.setGroups(groups.stream().map(CheckEntity::getId).collect(Collectors.toList()));
////            }
//            checkEntity.setTags(typeRelIds);
//            checkEntity.setChildren(checkRelIds);
//        }
        return checkEntity;
    }

    public Boolean toCheckEntityAddReview(String checkEntityId, Rating rating){
        CheckEntity checkEntity = this.getCheckEntity(checkEntityId);
        boolean saveIsSuccess = false;
        if (rating.getRating()==null){
            throw new RuntimeException("请填入评分");
        }
        if (Objects.nonNull(checkEntity)){
            rating.setPid(checkEntityId);
            String createBy = checkEntity.getCreateBy();
            if (createBy.equals(SecurityUtils.getUserId())){
                saveIsSuccess = ratingService.save(rating);
            }
        }
        return saveIsSuccess;
    }
}