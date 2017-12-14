package com.hdu.freeride.enums;

/**
 * Created by IntelliJ IDEA.
 * User: 33061
 * Date: 2017/12/14
 * Time: 14:49
 */
public enum ResultEnum {
    UNKNOW_ERROR(-1, "未知错误"),
    ERROR(1, ""),
    SUCCESS(0, "成功"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
