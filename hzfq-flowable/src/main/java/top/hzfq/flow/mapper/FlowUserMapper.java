package top.hzfq.flow.mapper;

import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.UserQueryImpl;
import top.hzfq.flow.model.entity.FlowUser;

import java.util.List;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/6/10 20:08
 */
public interface FlowUserMapper {

    List<User> findUserByQueryCriteria(UserQueryImpl query);

    long findUserCountByQueryCriteria(UserQueryImpl query);

    FlowUser findUserByUid(String uid);
}
