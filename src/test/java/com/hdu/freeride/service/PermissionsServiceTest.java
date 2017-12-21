package com.hdu.freeride.service;

import com.hdu.freeride.entity.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/18 19:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-mvc.xml", "/spring/spring-jpa.xml"})
public class PermissionsServiceTest {

    @Resource
    private PermissionsService permissionsService;

    @Test
    public void test() {
        permissionsService.revocationUserRole(20, Role.ADMIN);
    }

}