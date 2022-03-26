package top.hzfq.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/3/6 17:08
 */
@Data
public class User extends AbstractBaseInfo {

    @TableId(value = "uid")
    private Long userId;
    @TableField(value = "user_name")
    private String name;
    private Integer age;
    private String email;
    @EnumValue
    private Sex sex;

    public enum Sex {
        MAN,
        WOMAN;
    }
}
