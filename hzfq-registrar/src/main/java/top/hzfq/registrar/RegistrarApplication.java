package top.hzfq.registrar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/3/29
 */
@EnableEurekaServer
@SpringBootApplication
public class RegistrarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrarApplication.class, args);
    }

}
