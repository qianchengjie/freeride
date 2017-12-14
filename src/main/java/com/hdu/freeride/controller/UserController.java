package com.hdu.freeride.controller;

import com.hdu.freeride.service.UserService;
import com.hdu.freeride.entity.User;
import com.hdu.freeride.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/14 18:20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/register")
    public Object add(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult);
        }
        return ResultUtil.success(userService.save(user));
    }

    /**
     * 用户信息更新
     * @param user
     * @param bindingResult
     * @return
     */
    @PutMapping("/update")
    public Object update(@Valid User user, BindingResult bindingResult) {
        if (user.getId() == 0) {
            return ResultUtil.error("用户id不能为空");
        } else if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult);
        }
        userService.update(user);
        return ResultUtil.success();
    }

    /**
     * 查找所有用户
     * @return
     */
    @GetMapping("/findAll")
    public Object findAll() {
        return ResultUtil.success(userService.findAll());
    }

    /**
     * 根据页码查找用户信息
     * @param pageNum
     * @return
     */
    @GetMapping("/findAll/{pageNum}")
    public Object findAll(@PathVariable int pageNum) {
        return ResultUtil.success(userService.findAll(pageNum));
    }

    /**
     * 通过用户id查找用户信息
     * @param id
     * @return
     */
    @GetMapping("/find")
    public Object find(int id) {
        return ResultUtil.success(userService.find(id));
    }

    /**
     * 通过手机号查找用户
     * @param phone
     * @return
     */
    @GetMapping("/findByPhone")
    public Object findByPhone(String phone) {
        return ResultUtil.success(userService.findByPhone(phone));
    }


}
