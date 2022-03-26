package top.hzfq.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hzfq.mybatis.plus.entity.User;
import top.hzfq.mybatis.plus.mapper.UserMapper;
import top.hzfq.mybatis.plus.service.UserService;

/**
 * 实现类继承ServiceImpl,接口继承IService
 *
 * @author huzhifengqing@qq.com
 * @since 2022/3/6 20:03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
