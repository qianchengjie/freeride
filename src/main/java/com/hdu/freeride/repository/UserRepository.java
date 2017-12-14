package com.hdu.freeride.repository;

import com.hdu.freeride.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 33061
 * Date: 2017/12/14
 * Time: 14:49
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByPhone(String phone);


}
