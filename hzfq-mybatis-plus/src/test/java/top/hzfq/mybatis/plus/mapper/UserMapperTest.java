package top.hzfq.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import top.hzfq.mybatis.plus.entity.User;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/3/6 18:33
 */
@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void selectMapById() {
        System.out.println(userMapper.selectMapById(1L));
    }

    @Test
    public void selectList() {
        userMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void selectById() {
        System.out.println(userMapper.selectById(1L));
    }

    @Test
    public void selectBatchIds() {
        userMapper.selectBatchIds(Arrays.asList(1, 2)).forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        Map<String, Object> condition = new HashMap<>();
        condition.put("user_name", "Jone");
        condition.put("age", "18");
        userMapper.selectByMap(condition).forEach(System.out::println);
    }

    @Transactional
    @Rollback
    @Test
    public void insert() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail("zhangsan@hzfq.top");
        System.out.println(userMapper.insert(user));
        System.out.println(user.getUserId());
    }

    @Transactional
    @Rollback
    @Test
    public void deleteById() {
        System.out.println(userMapper.deleteById(1L));
    }

    @Transactional
    @Rollback
    @Test
    public void deleteByMap() {
        Map<String, Object> condition = new HashMap<>();
        condition.put("name", "Jone");
        condition.put("age", "18");
        System.out.println(userMapper.deleteByMap(condition));
    }

    @Transactional
    @Rollback
    @Test
    public void deleteBatchIds() {
        System.out.println(userMapper.deleteBatchIds(Arrays.asList(1, 2)));
    }

    @Transactional
    @Rollback
    @Test
    public void deleteByEntity() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        System.out.println(userMapper.deleteById(user));
        //必须指定ID
        user.setUserId(1L);
        System.out.println(userMapper.deleteById(user));
    }

    @Transactional
    @Rollback
    @Test
    public void updateById() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        System.out.println(userMapper.updateById(user));
        //必须指定ID
        user.setUserId(1L);
        System.out.println(userMapper.updateById(user));
    }

    //----------------------- Wrapper使用start -----------------------
    @Transactional
    @Rollback
    @Test
    public void delete() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", "tom@hzfq.top");
        System.out.println(userMapper.delete(queryWrapper));
    }

    @Transactional
    @Rollback
    @Test
    public void update() {
        User user = new User();
        user.setName("ZhangSan");
        user.setEmail("zhangsan@hzfq.top");

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.gt("age", 20)
                .like("user_name", "a")
                .or()
                .isNull("email");

        System.out.println(userMapper.update(user, updateWrapper));

        updateWrapper.clear();
        updateWrapper.like("user_name", "a")
                .and(w -> w
                        .gt("age", 20)
                        .or()
                        .isNull("email"));
        System.out.println(userMapper.update(user, updateWrapper));
    }

    @Test
    public void selectMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name", "age", "email");
        userMapper.selectMaps(queryWrapper).forEach(System.out::println);
    }

    /**
     * 子查询示例
     */
    @Test
    public void selectListSubQuery() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("uid", "select uid from t_user where uid<=100");
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    @Transactional
    @Rollback
    @Test
    public void updateWrapper() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .like("user_name", "a")
                .and(w -> w
                        .gt("age", 20)
                        .or()
                        .isNull("email"));
        updateWrapper.set("user_name", "小黑");
        updateWrapper.set("email", "XiaoHei@hzfq.top");
        System.out.println(userMapper.update(null, updateWrapper));
    }


    @Test
    public void selectListCondition() {
        String username = "";
        Integer ageMax = 30;
        Integer ageMin = 20;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(username)) {
            queryWrapper.like("user_name", "a");
        }
        if (ageMin != null) {
            queryWrapper.ge("age", ageMin);
        }
        if (ageMax != null) {
            queryWrapper.le("age", ageMax);
        }
        userMapper.selectList(queryWrapper).forEach(System.out::println);

        queryWrapper.clear();
        System.out.println("----------------------------------------------");
        queryWrapper
                .like(StringUtils.isNoneBlank(username), "user_name", "a")
                .ge(ageMin != null, "age", ageMin)
                .le(ageMax != null, "age", ageMax);
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }
    //----------------------- Wrapper使用end -----------------------

    //----------------------- LambdaWrapper start -----------------------
    @Test
    public void lambdaQueryWrapper() {
        String username = "";
        Integer ageMax = 30;
        Integer ageMin = 20;

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .like(StringUtils.isNotBlank(username), User::getName, "a")
                .ge(ageMin != null, User::getAge, ageMin)
                .le(ageMax != null, User::getAge, ageMax);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Transactional
    @Rollback
    @Test
    public void lambdaUpdateWrapper() {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper
                .like(User::getName, "a")
                .and(w -> w
                        .gt(User::getAge, 20)
                        .or()
                        .isNull(User::getEmail));
        wrapper.set(User::getName, "小黑");
        wrapper.set(User::getEmail, "XiaoHei@hzfq.top");
        System.out.println(userMapper.update(null, wrapper));
    }
    //----------------------- LambdaWrapper end -----------------------

    //----------------------- Page Plugin start -----------------------
    @Test
    public void selectPage() {
        Page<User> page = new Page<>(2, 3);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        userMapper.selectPage(page, wrapper);

        System.out.println(page.getSize());
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
        page.getRecords().forEach(System.out::println);
    }

    @Test
    public void selectPageVO() {
        Page<User> page = new Page<>(2, 3);
        userMapper.selectPageVO(page, 12);

        System.out.println(page.getSize());
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
        page.getRecords().forEach(System.out::println);
    }

    //----------------------- Page Plugin end -----------------------
    //----------------------- Enum Start -----------------------
    @Transactional
    @Rollback
    @Test
    public void insertEnum() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail("zhangsan@hzfq.top");
        user.setSex(User.Sex.MAN);
        userMapper.insert(user);
    }

    @Test
    public void selectEnum() {
        System.out.println(userMapper.selectById(1500845875302359042L));
    }
    //----------------------- Enum End -----------------------
}