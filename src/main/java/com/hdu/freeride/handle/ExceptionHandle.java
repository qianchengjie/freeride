package com.hdu.freeride.handle;

import com.hdu.freeride.entity.Result;
import com.hdu.freeride.exception.MyException;
import com.hdu.freeride.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA.
 * User: 33061
 * Date: 2017/12/14
 * Time: 14:49
 */
@ControllerAdvice
public class ExceptionHandle extends RuntimeException{

    public final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            logger.error("【自定义异常】", e);
            return ResultUtil.error(myException.getResultEnum().getMsg());
        }
        logger.error("【系统异常】", e);
        return ResultUtil.error();
    }

}
