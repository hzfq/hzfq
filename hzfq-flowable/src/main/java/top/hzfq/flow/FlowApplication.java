package top.hzfq.flow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/27 17:04
 */
@MapperScan(basePackages = {"top.hzfq.flow.mapper"})
@SpringBootApplication
public class FlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowApplication.class, args);
    }
}
