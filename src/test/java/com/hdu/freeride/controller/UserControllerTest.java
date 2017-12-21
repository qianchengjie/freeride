package com.hdu.freeride.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * User: 33061
 * Date: 2017/12/14
 * Time: 14:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-mvc.xml", "/spring/spring-jpa.xml"})
public class UserControllerTest {

    @Resource
    private UserController userController;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * 测试方法
     */
    @Test
    public void test() {
    }
}