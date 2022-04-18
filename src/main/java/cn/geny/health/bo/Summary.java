package cn.geny.health.bo;

import cn.geny.health.po.Rating;
import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/4/16 23:06
 */

@Data
public class Summary {

    private Double totalRating;

    private int totalReview;


    private List<Ratings> ratings;

    @Data
    public static class Ratings{
        public String name;
        public int starCount;
        public int reviewCount;
        public int value;
    }

    private List<Rating> ratingList;
}