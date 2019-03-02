package idata.dmp.exception;

import idata.dmp.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: result-spring-boot-starter
 * @description:
 * @author: guoqingming
 * @create: 2018-12-12 22:55
 **/
@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Result bizException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("业务处理异常：",ex);
        BizException exception = (BizException) ex;
        return Result.createFail(exception.getMessage()).setCode(((BizException) ex).getCode());
    }


    @ExceptionHandler(ArgumentException.class)
    @ResponseBody
    public Result argumentException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("业务处理异常：",ex);
        ArgumentException exception = (ArgumentException) ex;
        return Result.createFail(exception.getMessage()).setCode(502);
    }

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public Result authException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("权限校验异常：",ex);
        AuthException exception = (AuthException) ex;
        return Result.createFail(exception.getMessage()).setCode(401);
    }



    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("内部处理异常：",ex);
        Exception exception = (Exception) ex;
        return Result.createFail(exception.getMessage()).setCode(500);
    }
}
