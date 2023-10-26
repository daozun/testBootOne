package com.example.testBootOne.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Result {
    private String code; // 返回码
    private String message; //返回信息
    private Object data; //返回数据

    private Result() {}

    private Result(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    private Result(String code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result create(String code, String message) {
        return new Result(code, message);
    }

    public static Result create(String code, String message, Object data) {
        return new Result(code, message, data);
    }
}
