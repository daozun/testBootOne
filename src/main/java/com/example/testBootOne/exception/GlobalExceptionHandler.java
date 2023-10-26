package com.example.testBootOne.exception;

import com.example.testBootOne.entity.Result;
import com.example.testBootOne.entity.StatusCode;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 找不到资源
     * 未找到处理器 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result noHandlerFoundExceptionHander(Exception e) {
        return Result.create(StatusCode.NOTFOUND, "接口不存在");
    }


    /**
     * 权限不足
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Result accessDeniedExceptionHander(Exception e) {
        return Result.create(StatusCode.ACCESSERROR, "拒绝访问");
    }

    /**
     * 请求方式错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodNotSupportedExceptionHandler(Exception e) {
        return Result.create(StatusCode.ERROR, "请求方式错误");
    }


    /**
     * controller参数异常/缺少
     *
     * @param e
     * @return
     */
    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            RequestRejectedException.class,
            BindException.class}
    )
    public Result missingServletRequestParameterException(Exception e) {
        return Result.create(StatusCode.ERROR, "参数异常");

    }

    /**
     * 单次上传文件过大
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result maxUploadSizeExceededException(Exception e) {
        return Result.create(StatusCode.ERROR, "文件过大");
    }

    /**
     * 客户端错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ClientAbortException.class)
    public Result clientAbortExceptionException(Exception e) {
        return Result.create(StatusCode.ERROR, "客户端错误");
    }

    /**
     * 断言抛出的错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result IllegalArgumentExceptionException(Exception e) {
        return Result.create(StatusCode.LOGINERROR, e.getMessage());
    }


    /**
     * 其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        return Result.create(StatusCode.SERVICEERROR, "服务异常 请联系管理员");
    }
}
