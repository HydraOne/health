package cn.geny.health.common;

import cn.geny.health.model.QueryBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/8 20:45
 */
public class QueryProducer<E> {
    private QueryProducer() {

    }

    private static final QueryProducer me = new QueryProducer();

    public static QueryProducer me() {
        return me;
    }

    @SuppressWarnings("unchecked")
    public QueryWrapper<E> executeQuery(QueryBody<E> queryBody) {
        QueryWrapper<E> queryWrapper = new QueryWrapper<>();
        Map<String, Map> conditions = queryBody.getConditions();
        if (conditions != null) {
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
}