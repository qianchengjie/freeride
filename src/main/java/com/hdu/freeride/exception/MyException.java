package com.hdu.freeride.exception;

import com.hdu.freeride.enums.ResultEnum;

public class MyException extends RuntimeException{

    private ResultEnum resultEnum;

    public MyException(String msg) {
        super(msg);
        this.resultEnum = ResultEnum.ERROR;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }
    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}