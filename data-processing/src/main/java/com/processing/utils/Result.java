package com.processing.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author chen
 * @createDate 2024/6/5 14:27
 */
@Data
@AllArgsConstructor
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public static<T> Result ok() {
        return new Result("200","success","");
    }
    public static<T> Result ok(T data) {
        return new Result("200","",data);
    }

    public static<T> Result ok(String msg, T data) {
        return new Result("200",msg,data);
    }

    public static<T> Result ok(String msg, T data, String code) {
        return new Result(code,msg,data);
    }

    public static<T> Result fail(String code, String msg) {
        return new Result("-1000", msg, "");
    }

    public static<T> Result fail(String msg) {
        return new Result("-1000",msg,"");
    }
    public static<T> Result fail(String msg, T data) {
        return new Result("-1000",msg,data);
    }


}
