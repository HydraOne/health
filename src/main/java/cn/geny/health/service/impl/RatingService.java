package cn.geny.health.service.impl;

import cn.geny.health.bo.Summary;
import cn.geny.health.mapper.RatingMapper;
import cn.geny.health.po.Rating;
import cn.geny.health.po.RatingRel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * TODO
 * @author wangjiahao
 * @date 2022/4/16 23:02
 */
@Service
public class RatingService extends ServiceImpl<RatingMapper, Rating> {

    @Autowired
    RatingRelService ratingRelService;

    public List<Rating> getRatingListByPid(String pid){
        List<RatingRel> ratingRelList = ratingRelService.list(new QueryWrapper<RatingRel>().eq("pid", pid));
        List<String> ratingIds = ratingRelList.stream().map(RatingRel::getRid).collect(Collectors.toList());
        return ratingIds.size()!=0?this.listByIds(ratingIds):null;
    }

    public Summary getSummaryByPid(String pid){
        List<Rating> ratingList = this.getRatingListByPid(pid);
        Summary summary = new Summary();
        List<RatingRel> ratingRels = ratingRelService.list(new QueryWrapper<RatingRel>().eq("pid", pid));
        List<String> currentContainRatingId = ratingRels.stream().map(RatingRel::getRid).collect(Collectors.toList());
        List<Rating> ratingsList = this.listByIds(currentContainRatingId);
        DoubleStream doubleStream = ratingList.stream().mapToDouble(Rating::getReating);
        Double average = doubleStream.average().getAsDouble();
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        Map<Integer, List<Rating>> groupsByRating = ratingList.stream().collect(Collectors.groupingBy(Rating::getReating));
        summary.setTotalRating(decimalFormat.format(average));
        summary.setTotalReview(ratingsList.size());
        List<Summary.Ratings> list = new ArrayList();
        groupsByRating.forEach((key,value)->{
            Summary.Ratings ratings = new Summary.Ratings();
//            ratings.setName(RatingType.);
            ratings.setReviewCount(value.size());
            ratings.setStarCount(value.size());
            ratings.setValue(key);
            list.add(ratings);
        });
        summary.setRatings(list);
        return summary;
    }

}