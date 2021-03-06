package cn.geny.health.service.impl;

import cn.geny.health.bo.Summary;
import cn.geny.health.constant.AssociationType;
import cn.geny.health.constant.CheckType;
import cn.geny.health.constant.Constants;
import cn.geny.health.mapper.CheckEntityMapper;
import cn.geny.health.po.Association;
import cn.geny.health.po.CheckEntity;
import cn.geny.health.po.Rating;
import cn.geny.health.utils.SecurityUtils;
import cn.geny.health.utils.UUIDUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
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
    FileService fileService;

    @Autowired
    RatingService ratingService;

    @Autowired
    AssociationService associationService;


    @Override
    public boolean save(CheckEntity checkEntity) {
        String uuid = UUIDUtil.generateUUID();
        List<Association> list = new ArrayList<>();
        checkEntity.setId(uuid);
        CheckType type = EnumUtils.getEnum(CheckType.class,checkEntity.getType());
        if (!type.equals(CheckType.Item)){
            List<String> children = checkEntity.getChildren();
            children.forEach(itemId -> list.add(new Association(uuid, itemId,AssociationType.Check.name())));
        }
        return super.save(checkEntity) && (list.size() == 0 || associationService.saveBatch(list));
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
            if (type.equals(CheckType.Plan)){
                children.forEach(itemId -> typeRels.add(new Association(checkEntity.getId(), itemId,AssociationType.Check.name())));
            }
            if (type.equals(CheckType.Group)){
                List<CheckEntity> list = this.list(new QueryWrapper<CheckEntity>().in("id", children));
                list.stream().filter((childrenEntity->{
                    CheckType childrenType = EnumUtils.getEnum(CheckType.class,childrenEntity.getType());
                    return childrenType.equals(CheckType.Item);
                })).forEach(item -> typeRels.add(new Association(checkEntity.getId(), item.getId(),AssociationType.Check.name())));
            }
        }
        associationService.saveBatch(typeRels);

        return saveIsSuccess;
    }

    public CheckEntity getCheckEntityTree(String id) {
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


    public Set<CheckEntity> getCheckEntityAllItem(List<String> checkEntityIds) {
        List<CheckEntity> checkEntitys = this.list(new QueryWrapper<CheckEntity>().in("id",checkEntityIds));
        Set<CheckEntity> checkResults = new HashSet<>();
        eachChildrenToList(checkEntitys,checkResults);
        return checkResults;
    }

//    eachTitle = (array,rowStack,allResult)=>{
//        // const result = [];
//        array.forEach(item=>{
//            const row = rowStack.map(item=>item);
//        row.push(item.id);
//            const {price,priceSale,createTime,name,type,description} = item;
//            const data = {hierarchy: row,name,price,priceSale,createTime,id:allResult.length,type,description};
//        allResult.push(data);
//        if (item.children.length > 0){
//            eachTitle(item.children,row,allResult);
//        }
//        })
//    }

    @SuppressWarnings("unchecked")
    public void eachChildrenToList(Collection<CheckEntity> collection,Set<CheckEntity> result){
        collection.forEach(checkEntity -> {
            CheckType type = EnumUtils.getEnum(CheckType.class,checkEntity.getType());
            if (CheckType.Item!=type){
                List<CheckEntity> children = getChildren(checkEntity.getId(),0);
                if (Objects.nonNull(children)&&children.size()>0){
                    eachChildrenToList(children,result);
                }
            }
            else {
                result.add(checkEntity);
            }
        });
    }

    public Boolean toCheckEntityAddReview(String checkEntityId, Rating rating){
        CheckEntity checkEntity = this.getCheckEntityTree(checkEntityId);
        boolean saveIsSuccess = false;
        if (rating.getRating()==null){
            throw new RuntimeException("???????????????");
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

    @SuppressWarnings("unchecked")
    public List<CheckEntity> getChildren(String checkEntityId,int deps){
        // TODO: 2022/5/3 ?????????????????????????????????????????? 
        List<CheckEntity> result = new ArrayList<>();
        if (deps>=Constants.MAX_DEPTH){
            return result;
        }
        List<Association> checkRels = associationService.list(new QueryWrapper<Association>().eq("id", checkEntityId).eq("type",AssociationType.Check.name()));
        List<String> childrenIds = checkRels.stream().map(Association::getCid).collect(Collectors.toList());
        if (childrenIds.size()!=0){
            result.addAll(this.list(new QueryWrapper<CheckEntity>().in("id", childrenIds)));
            result.forEach(checkEntity -> {
                checkEntity.setChildren(getChildren(checkEntity.getId(),deps + 1));
            });
        }
        return result;
    }
}