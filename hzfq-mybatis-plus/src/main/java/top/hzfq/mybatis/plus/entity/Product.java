package top.hzfq.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/3/7 22:22
 */
@Data
public class Product extends AbstractBaseInfo {

    @TableId
    private Long pid;
    private String name;
    private Integer price;
}
