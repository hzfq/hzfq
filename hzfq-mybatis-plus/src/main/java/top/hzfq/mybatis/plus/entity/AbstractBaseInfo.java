package top.hzfq.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/3/6 22:55
 */
public abstract class AbstractBaseInfo {

    @TableLogic
    private Boolean deleted;
    @Version
    private Integer revision;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }
}
