package com.liwell.cinema.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.Result;
import com.liwell.cinema.exception.ResultException;
import com.liwell.cinema.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/27
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ResultException.class})
    @ResponseBody
    public Result<Object> ResultExceptionHandle(ResultException e) {
        return ResultUtil.result(e.getCode(), e.getMessage(), e.getData());
    }

    @ExceptionHandler(value = {NotLoginException.class})
    @ResponseBody
    public Result<Object> notLoginError(NotLoginException nle) {
        switch (nle.getType()) {
            case NotLoginException.NOT_TOKEN:
                return ResultUtil.result(ResultEnum.LOGGING_ERROR, NotLoginException.NOT_TOKEN_MESSAGE);
            case NotLoginException.INVALID_TOKEN:
                return ResultUtil.result(ResultEnum.LOGGING_ERROR, NotLoginException.INVALID_TOKEN_MESSAGE);
            case NotLoginException.TOKEN_TIMEOUT:
                return ResultUtil.result(ResultEnum.LOGGING_ERROR, NotLoginException.TOKEN_TIMEOUT_MESSAGE);
            case NotLoginException.BE_REPLACED:
                return ResultUtil.result(ResultEnum.LOGGING_ERROR, NotLoginException.BE_REPLACED_MESSAGE);
            case NotLoginException.KICK_OUT:
                return ResultUtil.result(ResultEnum.LOGGING_ERROR, NotLoginException.KICK_OUT_MESSAGE);
        }
        return ResultUtil.result(ResultEnum.LOGGING_ERROR, NotLoginException.DEFAULT_MESSAGE);
    }

    @ExceptionHandler(value = {NotPermissionException.class, NotRoleException.class})
    @ResponseBody
    public Result<Object> permissionException(Exception e) {
        log.error("权限不足: ", e);
        return ResultUtil.fail(ResultEnum.PERMISSION_ERROR);
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public Result<Object> Handle(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error("接口【{}】不支持{}方法", request.getServletPath(), request.getMethod().toLowerCase());
        return ResultUtil.result(ResultEnum.INTERFACE_METHOD_ERROR, "接口不支持" + request.getMethod().toUpperCase() + "方法");
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public Result<Object> argumentError(MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldErrors().get(0);
        return ResultUtil.result(ResultEnum.PARAMETER_ERROR,
                ResultEnum.PARAMETER_ERROR.getMessage() + "," + fieldError.getField() + fieldError.getDefaultMessage());
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class, Exception.class})
    @ResponseBody
    public Result<Object> argumentTypeError(Exception e, HttpServletRequest request) {
        log.error("接口【{}】的{}方法发生异常", request.getServletPath(), request.getMethod().toLowerCase());
        log.error(e.getMessage(), e);
        return ResultUtil.fail();
    }

}
