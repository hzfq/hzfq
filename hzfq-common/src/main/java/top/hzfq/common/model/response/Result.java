package top.hzfq.common.model.response;

/**
 * 响应返回结果
 *
 * @author huzhifengqing@qq.com
 * @since 2022/2/28 22:24
 */
public class Result {

    private int code;
    private String msg;
    private String exception;
    private Object data;

    private Result() {
    }

    public static Result of(ResultEnum resultEnum) {
        return of(resultEnum.getCode(), resultEnum.getMsg(), null, null);
    }

    public static Result of(int code, String msg) {
        return of(code, msg, null, null);
    }

    public static Result of(ResultEnum resultEnum, Object data) {
        return of(resultEnum.getCode(), resultEnum.getMsg(), null, data);
    }

    public static Result of(int code, String msg, Object data) {
        return of(code, msg, null, data);
    }


    public static Result of(ResultEnum resultEnum, String exception) {
        return of(resultEnum.getCode(), resultEnum.getMsg(), exception, null);
    }

    public static Result of(int code, String msg, String exception) {
        return of(code, msg, exception, null);
    }

    public static Result of(ResultEnum resultEnum, String exception, Object data) {
        return of(resultEnum.getCode(), resultEnum.getMsg(), exception, data);
    }

    public static Result of(int code, String msg, String exception, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setException(exception);
        result.setData(data);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
