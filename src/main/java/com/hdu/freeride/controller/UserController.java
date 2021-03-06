package com.hdu.freeride.controller;

import com.hdu.freeride.service.UserService;
import com.hdu.freeride.entity.User;
import com.hdu.freeride.util.ResultUtil;
import org.hibernate.annotations.Parameter;
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
    public Object register(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult);
        }
        return ResultUtil.success(userService.register(user));
    }

    /**
     * 用户通过用户名和密码或者手机号和密码登录
     * @param name
     * @param phone
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestParam(name = "name", defaultValue = "") String name,
                        @RequestParam(name = "phone", defaultValue = "") String phone,
                        String password) {
        return ResultUtil.success(userService.login(name, phone, password));
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
    @GetMapping("/find/{id}")
    public Object find(@PathVariable Integer id) {
        if (id == null) {
            return ResultUtil.error("缺少参数");
        }
        return ResultUtil.success(userService.findOne(id));
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

    /**
     * 用户充值钱包
     * @param userId
     * @param price
     * @return
     */
    @PostMapping("/recharge")
    public Object recharge(Integer userId, double price) {
        if (userId == null) {
            return ResultUtil.error("用户id不能为空");
        }
        return ResultUtil.success(userService.recharge(userId, price));
    }

    /**
     * 用户提现
     * @param userId
     * @param price
     * @return
     */
    @PostMapping("/withdrawals")
    public Object withdrawals(Integer userId, double price) {
        if (userId == null) {
            return ResultUtil.error("用户id不能为空");
        }
        return ResultUtil.success(userService.withdrawals(userId, price));
    }

    /**
     * 分页查找交易明细
     * @param userId
     * @param type
     * @param pageNum
     * @return
     */
    @GetMapping("findAllTD/{pageNum}")
    public Object findAllTransactionDetial(Integer userId, Integer type,@PathVariable Integer pageNum) {
        return ResultUtil.success(userService.findAllTransactionDetial(userId, type, pageNum));
    }


}
