package com.hdu.freeride.service;

import com.hdu.freeride.entity.User;
import com.hdu.freeride.exception.MyException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: 33061
 * Date: 2017/12/14
 * Time: 16:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-mvc.xml", "/spring/spring-jpa.xml"})
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void test() {
        User user = new User();
        user.setPassword("132456asd");
        user.setName("hahah");
        user.setPhone("18667332212");
        System.out.println( userService.register(user).toString());
    }

}