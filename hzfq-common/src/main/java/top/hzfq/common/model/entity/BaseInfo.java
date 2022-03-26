package top.hzfq.common.model.entity;

import java.sql.Timestamp;

/**
 * 基础信息
 *
 * @author huzhifengqing@qq.com
 * @since 2022/2/26 10:53
 */
public abstract class BaseInfo {

    private String creator;
    private Timestamp createTime;
    private String updater;
    private Timestamp updateTime;
    private Boolean deleted;
    private Integer revision;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

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

    @Override
    public String toString() {
        return "BaseInfo{" +
                "creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", updater='" + updater + '\'' +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                ", revision=" + revision +
                '}';
    }
}
