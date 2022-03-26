package top.hzfq.mybatis.plus.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.hzfq.mybatis.plus.entity.Product;

import javax.annotation.Resource;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/3/7 22:23
 */
@SpringBootTest
public class ProductMapperTest {

    @Resource
    private ProductMapper productMapper;

    @Test
    public void product() {
        Product product1 = productMapper.selectById(1);
        System.out.println(product1);

        Product product2 = productMapper.selectById(1);
        System.out.println(product2);

        product1.setPrice(product1.getPrice() + 50);
        productMapper.updateById(product1);

        product2.setPrice(product2.getPrice() - 30);
        productMapper.updateById(product2);

        System.out.println(productMapper.selectById(1L));
    }

    @Test
    public void productXiaoWan() {

    }
}