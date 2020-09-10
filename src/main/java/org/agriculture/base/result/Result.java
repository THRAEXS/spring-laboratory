package org.agriculture.base.result;

import java.io.Serializable;

/**
 * @author MLeo
 * @date 2020/08/11 10:09
 */
public class Result<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    public static <C> Result<C> ok(C data) {
        return new Result<C>().setCode(2000).setData(data);
    }

    public static <C> Result<C> ok(C data, Integer code) {
        return new Result<C>().setCode(code).setData(data);
    }

    public static <C> Result<C> ok(C data, Integer code, String message) {
        return ok(data, code).setMessage(message);
    }

    public static Result fail(String message) {
        return fail(message, 5000);
    }

    public static Result fail(String message, Integer code) {
        return new Result().setCode(code).setMessage(message);
    }
    
    public Integer getCode() {
        return code;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}
