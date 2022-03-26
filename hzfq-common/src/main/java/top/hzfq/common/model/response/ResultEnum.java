package top.hzfq.common.model.response;

/**
 * 响应返回结果
 *
 * @author huzhifengqing@qq.com
 * @since 2022/2/28 22:24
 */
public enum ResultEnum {

    SUCCESS(200, "请求成功"),
    INNER_ERROR(100001, "服务内部错误");

    private final int code;
    private final String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
