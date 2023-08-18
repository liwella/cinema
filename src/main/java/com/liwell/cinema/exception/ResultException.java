package com.liwell.cinema.exception;

import com.liwell.cinema.domain.enums.ResultEnum;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/25
 */
public class ResultException extends RuntimeException {

    private Integer code;

    private Object data;

    public ResultException(Integer code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public ResultException(ResultEnum resultEnum, Object data) {
        super(resultEnum.getDescription());
        this.code = resultEnum.getValue();
        this.data = data;
    }

    public ResultException(ResultEnum resultEnum) {
        super(resultEnum.getDescription());
        this.code = resultEnum.getValue();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
