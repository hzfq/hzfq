package top.hzfq.flowable.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.hzfq.common.model.response.Result;
import top.hzfq.common.model.response.ResultEnum;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/28 22:17
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Throwable.class})
    public Result Throwable(Throwable e) {
        return Result.of(ResultEnum.INNER_ERROR, e.getLocalizedMessage());
    }
}
