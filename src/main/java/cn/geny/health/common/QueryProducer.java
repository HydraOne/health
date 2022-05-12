package cn.geny.health.common;

import cn.geny.health.manager.factory.AsyncFactory;
import cn.geny.health.model.QueryBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.*;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/8 20:45
 */
public class QueryProducer<E> {

    private static final Logger logger = LoggerFactory.getLogger(AsyncFactory.class);

    private QueryProducer() {

    }

    private static final QueryProducer me = new QueryProducer();

    public static QueryProducer me() {
        return me;
    }

    @SuppressWarnings("unchecked")
    public QueryWrapper<E> generatePageQuery(QueryBody<E> queryBody,Class<E> entity){
        QueryWrapper<E> queryWrapper = new QueryWrapper<>();
//        queryWrapper.setEntityClass(entity);
        Map<String, Map> conditions = queryBody.getConditions();


        //** 小驼峰转下划线

        //jackson
//        List<String> timeFields = queryBody.getTimeFields();
        if (conditions != null) {
            camelToUpLowerCase(conditions);
//            convertStrFieldToDate(conditions,timeFields);
            Map<String, Object> equalsCondition = equalsCondition = conditions.get(QueryBody.EQUALS_FIELD);
            if (equalsCondition != null) {
                equalsCondition.forEach(queryWrapper::eq);
            }
            Map<String, Object> notEqualsCondition = conditions.get(QueryBody.NOT_EQUALS_FIELD);
            if (notEqualsCondition != null) {
                notEqualsCondition.forEach(queryWrapper::ne);
            }
            Map<String, Object> likeCondition = conditions.get(QueryBody.LIKE_FIELD);
            if (likeCondition != null) {
                likeCondition.forEach(queryWrapper::like);
            }
            Map<String, Map> betweenCondition = conditions.get(QueryBody.BETWEEN_FIELD);
            if (betweenCondition != null) {
                betweenCondition.forEach((key, param) -> {
                    queryWrapper.between(key, param.get(QueryBody.START_FIELD), param.get(QueryBody.END_FIELD));
                });
            }
            Map<String, List> inCondition = conditions.get(QueryBody.IN_FIELD);
            if (inCondition != null) {
                inCondition.forEach(queryWrapper::in);
            }
            Map<String, List> notInCondition = conditions.get(QueryBody.NOT_IN_FIELD);
            if (notInCondition != null) {
                notInCondition.forEach(queryWrapper::notIn);
            }
            Map<String, Object> maxCondition = conditions.get(QueryBody.MAX_FIELD);
            if (maxCondition != null) {
                maxCondition.forEach(queryWrapper::le);
            }
            Map<String, Object> minCondition = conditions.get(QueryBody.MIN_FIELD);
            if (minCondition != null) {
                minCondition.forEach(queryWrapper::ge);
            }
        }
        return queryWrapper;
    }

    @SuppressWarnings("unchecked")
//    public void convertStrFieldToDate(Map<String, Map> conditions,List<String> timeFields) {
//        if (timeFields ==null){
//            return;
//        }
//        Set<String> timeFieldsSet = new HashSet<>(timeFields);
//        conditions.forEach((key, objVal) -> {
//            if (QueryBody.isSingleField(key)) {
//                Map<String, Object> map = objVal;
//                map.forEach((fieldName, fieldObjVal) -> {
//                    String fieldVal = (String) fieldObjVal;
//                    if (timeFieldsSet.contains(fieldName) && fieldVal != null) {
//                        try {
//                            map.replace(fieldName, DateUtils.parseDate(fieldVal));
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//        });
//    }

    public void camelToUpLowerCase(Map<String, Map> conditions){
        HashMap camelcaseExtMap = new HashMap();
        conditions.forEach((key, objVal) -> {
            conditions.replace(key,mapKeyCamelToLowCase(objVal));
        });
    }

    public Map mapKeyCamelToLowCase(Map<String,Object> camelMap){
        HashMap<String,Object> lowCaseMap = new HashMap<>();
        camelMap.forEach((key,objVal)->{
            lowCaseMap.put(key.replaceAll("([a-z])([A-Z])", "$1_$2").toUpperCase(),objVal);
        });
        return lowCaseMap;
    }

    public void convertStrFieldToDate(Map<String, Map> conditions,List<String> timeFields) {
        if (timeFields ==null){
            return;
        }
        Set<String> timeFieldsSet = new HashSet<>(timeFields);
        conditions.forEach((key, objVal) -> {
            if (QueryBody.isSingleField(key)) {
                Map<String, Object> map = objVal;
                map.forEach((fieldName, fieldObjVal) -> {
                    String fieldVal = (String) fieldObjVal;
                    if (timeFieldsSet.contains(fieldName) && fieldVal != null) {
                        try {
                            map.replace(fieldName, DateUtils.parseDate(fieldVal));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}