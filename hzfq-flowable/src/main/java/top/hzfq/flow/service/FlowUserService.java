package top.hzfq.flow.service;

import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.UserQueryImpl;
import top.hzfq.flow.model.entity.FlowUser;
import top.hzfq.flow.model.request.FlowUserDTO;

import java.util.List;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/6/10 21:13
 */
public interface FlowUserService {

    List<User> findUserByQueryCriteria(UserQueryImpl query);

    long findUserCountByQueryCriteria(UserQueryImpl query);

    FlowUser findUserByUid(String uid);

    List<FlowUser> findUsers(FlowUserDTO user);

}
