package top.hzfq.flow.model.request;

import top.hzfq.flow.model.entity.FlowUser;

import java.util.List;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/6/10 22:25
 */
public class FlowUserDTO extends FlowUser {

    private List<String> uids;
    private String usernameLike;
    private String emailLike;

    public List<String> getUids() {
        return uids;
    }

    public void setUids(List<String> uids) {
        this.uids = uids;
    }

    public String getUsernameLike() {
        return usernameLike;
    }

    public void setUsernameLike(String usernameLike) {
        this.usernameLike = usernameLike;
    }

    public String getEmailLike() {
        return emailLike;
    }

    public void setEmailLike(String emailLike) {
        this.emailLike = emailLike;
    }

}
