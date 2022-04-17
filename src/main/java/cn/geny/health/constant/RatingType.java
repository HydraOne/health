package cn.geny.health.constant;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/4/16 23:35
 */
public enum RatingType {
    /**
     * 非常满意
     */
    A(5),
    /**
     * 满意
     */
    B(4),
    /**
     * 一般
     */
    C(3),
    /**
     * 差劲
     */
    D(2),
    /**
     * 非常差
     */
    E(1);

    RatingType(int value) {
    }

//    public String valueOf(){
//
//    }
//
//
//    private static HashMap<Integer,RatingType> ratingEnumCache;
//
//    static {
//        RatingType[] ratingTypes = values();
//        for (RatingType ratingType : ratingTypes) {
//            ratingEnumCache.put(ratingType,ratingType);
//        }
//    }
//
//    public RatingType getEnumByValue(int value){
//
//    }
}
