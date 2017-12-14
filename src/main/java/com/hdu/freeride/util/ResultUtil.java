package com.hdu.freeride.util;

import com.hdu.freeride.entity.Result;
import com.hdu.freeride.enums.ResultEnum;
import org.springframework.validation.BindingResult;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/14 18:20
 */
public class ResultUtil {

    /**
     * 带data参数的成功
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 不带data参数的成功
     * @return
     */
    public static Result success() {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        return result;
    }
    /**
     * 未知错误
     * @return
     */
    public static Result error() {
        Result result = new Result();
        result.setCode(ResultEnum.UNKNOW_ERROR.getCode());
        result.setMsg(ResultEnum.UNKNOW_ERROR.getMsg());
        return result;
    }
    /**
     * 自定义错误
     * @param msg
     * @return
     */
    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }

    /**
     * 参数绑定错误
     * @param bindingResult
     * @return
     */
    public static Result error(BindingResult bindingResult) {
        return error(bindingResult.getFieldError().getDefaultMessage());
    }
}
