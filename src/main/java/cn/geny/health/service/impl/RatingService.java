package cn.geny.health.service.impl;

import cn.geny.health.bo.Summary;
import cn.geny.health.bo.User;
import cn.geny.health.common.RedisCache;
import cn.geny.health.constant.Constants;
import cn.geny.health.mapper.RatingMapper;
import cn.geny.health.po.Account;
import cn.geny.health.po.Rating;
import cn.geny.health.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * TODO
 * @author wangjiahao
 * @date 2022/4/16 23:02
 */
@Service
@CacheNamespace(implementation= RedisCache.class,eviction= RedisCache.class)
public class RatingService extends ServiceImpl<RatingMapper, Rating> {

    @Autowired
    RedisCache redisCache;

    @Autowired
    UserService userService;


    public List<Rating> getCurrentRatingListByPid(String pid){
        return this.list(new QueryWrapper<Rating>().eq("pid",pid));
    }

    public Summary getOverview(String pid){
        Summary summary = new Summary();
        List<Rating> ratingList = this.getCurrentRatingListByPid(pid);
        DoubleStream doubleStream = ratingList.stream().filter(item->item.getRating()!=null).mapToDouble(Rating::getRating);
        OptionalDouble optionalDouble = doubleStream.average();
        Double average = 0D;
        if (optionalDouble.isPresent()){
            average = optionalDouble.getAsDouble();
        }
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");
        Map<Integer, List<Rating>> groupsByRating = ratingList.stream().collect(Collectors.groupingBy(Rating::getRating));
        summary.setTotalRating(decimalFormat.format(average));
        summary.setTotalReview(ratingList.size());
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
        redisCache.setCacheObject(Constants.OVERVIEW_KEY + pid,summary);
        return summary;
    }

    public Page<Rating> getRatingsPage(String pid,int start,int size){
        Page<Rating> page = new Page<>(start, size);
        QueryWrapper<Rating> queryWrapper = new QueryWrapper<>();
        Page<Rating> ratingPage = this.page(page, queryWrapper.eq("pid", pid).orderByDesc("create_time"));
        List<Rating> records = ratingPage.getRecords();
        List<String> createByIds = records.stream().map(Rating::getCreateBy).collect(Collectors.toList());
        List<Account> accounts = new ArrayList<>();
        if (createByIds.size()>0){
            accounts.addAll(userService.listByIds(createByIds));
        }
        Map<String, String> avatarRel = accounts.stream().map(SecurityUtils::convertAccountToUser).collect(Collectors.toMap(Account::getId, User::getPhotoURL));
        records.forEach(rating -> {
            rating.setAvatarUrl(avatarRel.get(rating.getCreateBy()));
            rating.setIsPurchased(true);
            rating.setHelpful(new Random().nextInt(10000));
        });
        return ratingPage;
    }

    public Summary getSummary(String pid){
        Summary summary = getOverview(pid);
        return summary;
    }
}