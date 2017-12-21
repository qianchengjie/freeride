package com.hdu.freeride.repository;

import com.hdu.freeride.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/14 18:20
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByName(String name);

    public User findByPhone(String phone);

    public User findByNameAndPasswordOrPhoneAndPassword(String name, String password, String phone, String password1);

}
