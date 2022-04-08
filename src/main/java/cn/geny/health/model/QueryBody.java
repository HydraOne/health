package cn.geny.health.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public final static String TIME_FIELD = "timeField";
    public final static String START_FIELD = "start";
    public final static String END_FIELD = "end";
    private final static Set<String> singleFields;

    static {
        singleFields = new HashSet<>();
        singleFields.add(EQUALS_FIELD);
        singleFields.add(NOT_EQUALS_FIELD);
        singleFields.add(LIKE_FIELD);
        singleFields.add(MAX_FIELD);
        singleFields.add(MIN_FIELD);
    }

    public static boolean isSingleField(String filedName) {
        return singleFields.contains(filedName);
    }

    @JsonAlias("start")
    private int start;

    @JsonAlias("size")
    private int size;

    @JsonAlias("conditions")
    private Map<String, Map> conditions;

    @JsonAlias("timeField")
    private List<String> timeFields;
}