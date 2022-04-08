package cn.geny.health.exception;

import cn.geny.health.common.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限校验异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult handleGlobalRuntimeExpetion(RuntimeException e,HttpServletRequest request){
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}'", requestURI, e);
        return AjaxResult.error(e.getMessage());
    }
}