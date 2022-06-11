package top.hzfq.flow.service.impl;

import org.flowable.common.engine.impl.context.Context;
import org.flowable.common.engine.impl.db.DbSqlSession;
import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.UserQueryImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.springframework.stereotype.Service;
import top.hzfq.flow.mapper.FlowUserMapper;
import top.hzfq.flow.model.entity.FlowUser;
import top.hzfq.flow.model.request.FlowUserDTO;
import top.hzfq.flow.service.FlowUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/6/10 21:13
 */
@Service
public class FlowUserServiceImpl implements FlowUserService {

    @Resource
    private FlowUserMapper userMapper;

    protected DbSqlSession getDbSqlSession() {
        return Context.getCommandContext().getSession(DbSqlSession.class);
    }

    @Override
    public List<User> findUserByQueryCriteria(UserQueryImpl query) {
        return userMapper.findUserByQueryCriteria(query);
    }

    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl query) {
        return userMapper.findUserCountByQueryCriteria(query);
    }

    @Override
    public FlowUser findUserByUid(String uid) {
        return userMapper.findUserByUid(uid);
    }

    @Override
    public List<FlowUser> findUsers(FlowUserDTO user) {
        return null;
    }
}
