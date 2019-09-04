package com.barista.result;

/**
 * 返回码
 *
 * @ClassName ResultCode
 * @Author zhaoth
 * @Date 2019/8/10 15:10
 * @Version 1.0
 */
public enum ResultCode {
    SUCCESS(200, "操作成功"),

    NEED_LOGIN(0, "需要登录"),
    CHECK_CODE_ERROR(10, "验证码错误"),
    PASSWORD_ERROR(20, "密码错误"),
    ILLEGAL_PARAM(30, "不合法参数"),

    NO_PERMISSION(100, "没有操作权限"),
    IS_USING(110,"正在被使用，无法删除"),
    DUPLICATE_INDEX(120,"索引重复"),
    ILLEGAL_STATUS(130,"父账号冻结，无法操作"),

    CUSTOM_ERROR(300,"自定义错误信息"),


    SERVER_ERROR(500, "服务器操作失败");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
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
    }}
