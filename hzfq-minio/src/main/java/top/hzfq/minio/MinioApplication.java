package top.hzfq.minio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/3/26 9:33
 */
@SpringBootApplication
public class MinioApplication {

    private static final Logger logger = LoggerFactory.getLogger(MinioApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MinioApplication.class, args);
        logger.warn("Minio Application Started...");
        logger.error("Minio Application Started...");
    }
}
