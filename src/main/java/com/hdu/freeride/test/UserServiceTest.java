package com.hdu.freeride.test;

import com.hdu.freeride.entity.User;
import com.hdu.freeride.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.net.URL;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by IntelliJ IDEA.
 * User: 33061
 * Date: 2017/12/14
 * Time: 14:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
//bean的配置文件路径，这个是Test类的classpath路径，如果是Spring推荐的目录结构，应该在：项目目录/src/test/resources/里
@ContextConfiguration(locations = {"/spring/spring-jpa.xml"})
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * 测试方法
     */
    @org.junit.Test
    public void test() {
        Page<User> page = userService.findAll(1);
        System.out.println(page);
    }
}