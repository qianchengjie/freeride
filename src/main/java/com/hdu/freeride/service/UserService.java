package com.hdu.freeride.service;

import com.hdu.freeride.entity.User;
import com.hdu.freeride.repository.UserRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 33061
 * Date: 2017/12/14
 * Time: 14:49
 */
@Service
public class UserService {

    private final static int ONE_PAGE_SUM = 10;

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户注册
     * @param user
     * @return
     */
    public User save(User user) {
        user.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm;ss").format(new Date()));
        return userRepository.save(user);
    }

    /**
     * 通过用户id查找用户
     * @param id
     * @return
     */
    public User find(int id) {
        return userRepository.findOne(id);
    }

    /**
     * 找出所有用户
     * @return
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 通过手机号查找用户
     * @param phone
     * @return
     */
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    /**
     * 更新用户信息
     * @param user
     */
    public void update(User user) {
        userRepository.save(user);
    }

    /**
     * 分页查找用户列表
     * @param pageNum
     * @return
     */
    public Page<User> findAll(int pageNum) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNum-1, ONE_PAGE_SUM, sort);
        return userRepository.findAll(pageable);
    }



}
