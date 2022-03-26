package top.hzfq.mybatis.plus.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import top.hzfq.mybatis.plus.entity.User;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/3/6 20:05
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void count() {
        System.out.println(userService.count());
    }

    @Transactional
    @Rollback
    @Test
    public void saveBatch() {
        List<User> users = new ArrayList<>();
        User zhangsan = new User();
        zhangsan.setName("张三");
        zhangsan.setAge(23);
        zhangsan.setEmail("zhangsan@hzfq.top");
        users.add(zhangsan);

        User lisi = new User();
        lisi.setName("李四");
        lisi.setAge(25);
        lisi.setEmail("lisi@hzfq.top");
        users.add(lisi);

        System.out.println(userService.saveBatch(users));
    }
}