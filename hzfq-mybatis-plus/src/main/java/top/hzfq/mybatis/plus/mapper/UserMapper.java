package top.hzfq.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import top.hzfq.mybatis.plus.entity.User;

import java.util.Map;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/3/6 18:24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    Map<String, Object> selectMapById(Long userId);

    Page<User> selectPageVO(Page<User> page, Integer age);
}
