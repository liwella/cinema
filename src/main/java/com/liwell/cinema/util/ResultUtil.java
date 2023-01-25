package com.liwell.cinema.util;

import com.liwell.cinema.domain.enums.ResultEnum;
import com.liwell.cinema.domain.po.Result;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
public class ResultUtil {

    public static <T> Result<T> result(Integer code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    public static <T> Result<T> success() {
        return success(ResultEnum.SUCCESS);
    }

    public static <T> Result<T> success(ResultEnum resultEnum, T data) {
        return result(resultEnum.getValue(), resultEnum.getMessage(), data);
    }

    public static <T> Result<T> success(ResultEnum resultEnum) {
        return success(resultEnum, null);
    }


    public static <T> Result<T> fail(String msg) {
        return result(ResultEnum.FAIL.getValue(), msg, null);
    }

    public static <T> Result<T> fail(ResultEnum resultEnum, T data) {
        return result(resultEnum.getValue(), resultEnum.getMessage(), data);
    }

    public static <T> Result<T> fail(ResultEnum resultEnum) {
        return fail(resultEnum, null);
    }

}
