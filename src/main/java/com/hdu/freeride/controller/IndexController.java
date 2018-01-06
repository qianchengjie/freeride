package com.hdu.freeride.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/26 0:45
 */
@Controller("/")
public class IndexController {

    @RequestMapping("")
    public String index() {
        return "index";
    }

}
