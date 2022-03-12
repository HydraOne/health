package cn.geny.health.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/8 17:24
 */
@Data
public class QueryBody<E> {
    public final static String EQUALS_FIELD = "equalsField";
    public final static String LIKE_FIELD = "likeField";
    public final static String BETWEEN_FIELD = "betweenField";
    public final static String IN_FIELD = "inField";
    public final static String NOT_IN_FIELD = "notInField";
    public final static String MAX_FIELD = "maxField";
    public final static String MIN_FIELD = "minField";
    public final static String NOT_EQUALS_FIELD = "notEqualsField";
    public final static String START_FIELD = "start";
    public final static String END_FIELD = "end";
    int start;
    int size;
    HashMap<String, Map> conditions;
}