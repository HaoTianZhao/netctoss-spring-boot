package com.barista.result;

/**
 * 统一返回json格式
 *
 * @ClassName result
 * @Author zhaoth
 * @Date 2019/8/10 14:19
 * @Version 1.0
 */
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    public static Result fail(ResultCode code) {
        return new Result(code);
    }

    public static Result fail(String msg) {
        return new Result(ResultCode.CUSTOM_ERROR.getCode(), msg);
    }

    public boolean isSuccess(){
        return this.code == 200;
    }

    private Result(ResultCode code, T data) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
    }

    private Result(ResultCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
