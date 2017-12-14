package com.hdu.freeride.exception;

/**
 * Created by IntelliJ IDEA.
 * User: 33061
 * Date: 2017/12/14
 * Time: 14:49
 */
import com.hdu.freeride.enums.ResultEnum;

public class MyException extends RuntimeException{

    private ResultEnum resultEnum;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.resultEnum = ResultEnum.UNKNOW_ERROR;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }
    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}