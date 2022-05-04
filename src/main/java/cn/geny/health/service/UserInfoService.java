package cn.geny.health.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.geny.health.po.UserInfo;
import cn.geny.health.mapper.UserInfoMapper;
/** 
 * TODO
 * @author wangjiahao
 * @date 2022/5/4 21:18
 */
@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> {

}
