package top.hzfq.flow.flowable.idm;

import org.flowable.idm.api.User;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.UserQueryImpl;
import org.flowable.idm.engine.impl.persistence.entity.data.impl.MybatisUserDataManager;
import org.springframework.stereotype.Service;
import top.hzfq.flow.model.request.FlowUserDTO;
import top.hzfq.flow.service.FlowUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义用户数据管理器
 *
 * @author huzhifengqing@qq.com
 * @since 2022/6/10 19:59
 */
@Service
public class FlowUserDataManager extends MybatisUserDataManager {

    @Resource
    private FlowUserService userService;

    public FlowUserDataManager(IdmEngineConfiguration idmEngineConfiguration) {
        super(idmEngineConfiguration);
    }

    @Override
    public List<User> findUserByQueryCriteria(UserQueryImpl query) {
//        FlowUserDTO user = new FlowUserDTO();
//        user.setUid(query.getId());
//        user.setUid(query.getIdIgnoreCase());
//        user.setUsername(query.getFirstName());
//        user.setUsername(query.getLastName());
//        user.setUsername(query.getDisplayName());
//        user.setUsernameLike("%" + query.getFirstNameLike() + "%");
//        user.setUsernameLike("%" + query.getFirstNameLikeIgnoreCase() + "%");
//        user.setEmail(query.getEmail());
//        user.setEmailLike("%" + query.getEmailLike() + "%");
//        return userService.findUserByQueryCriteria(query);
        return super.findUserByQueryCriteria(query);
    }

    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl query) {
//        return userService.findUserCountByQueryCriteria(query);
        return super.findUserCountByQueryCriteria(query);
    }
//
//    @Override
//    public List<User> findUsersByPrivilegeId(String privilegeId) {
//        return null;
//    }
//
//    @Override
//    public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap) {
//        return null;
//    }
//
//    @Override
//    public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
//        return 0;
//    }
//
//    @Override
//    public UserEntity create() {
//        return null;
//    }
//
//    @Override
//    public UserEntity findById(String entityId) {
//        FlowUser flowUser = userService.findUserByUid(entityId);
//        UserEntity userEntity = new UserEntityImpl();
//        userEntity.setId(flowUser.getUid());
//        userEntity.setDisplayName(flowUser.getUsername());
//        userEntity.setPassword(flowUser.getPassword());
//        userEntity.setEmail(flowUser.getEmail());
//        return userEntity;
//    }
//
//    @Override
//    public void insert(UserEntity entity) {
//
//    }
//
//    @Override
//    public UserEntity update(UserEntity entity) {
//        return null;
//    }
//
//    @Override
//    public void delete(String id) {
//    }
//
//    @Override
//    public void delete(UserEntity entity) {
//
//    }
}
