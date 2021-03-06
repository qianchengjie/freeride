package com.hdu.freeride.service;

import com.hdu.freeride.entity.*;
import com.hdu.freeride.exception.MyException;
import com.hdu.freeride.repository.TransactionDetailsRepository;
import com.hdu.freeride.repository.UserRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/14 18:20
 */
@Service
public class UserService {

    private final static int ONE_PAGE_SUM = 10;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionsService permissionsService;

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    /**
     * 用户注册
     * @param user
     * @return
     */
    public User register(User user) {
        if (user.getPassword().length() > 16 || user.getPassword().length() < 8) {
            throw new MyException("密码长度为8-16位");
        } else if (userRepository.findByName(user.getName()) != null) {
            throw new MyException("用户名已存在");
        } else if (userRepository.findByPhone(user.getPhone()) != null) {
            throw new MyException("手机号已存在");
        }
        user.setDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        user.setLastOnlineTime(user.getDate());
        String md5Pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pwd);
        user.setNikeName(user.getName());
        user.setHeadImg("default-head-img.png");
        user.setStatus(0);
        user = userRepository.save(user);

        permissionsService.addUserRole(user.getId(), Role.PASSENGER);
        return user;
    }

    /**
     * 用户登录
     * @param name
     * @param phone
     * @param password
     */
    public User login(String name, String phone, String password) {
        String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userRepository.findByNameAndPasswordOrPhoneAndPassword(name, md5Pwd, phone, md5Pwd);
        if (user == null) {
            throw new MyException("帐号或密码错误！");
        }
        user.setLastOnlineTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        userRepository.save(user);
        return user;
    }

    /**
     * 通过用户id查找用户
     * @param id
     * @return
     */
    public User findOne(int id) {
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
        if (user.getPassword() != userRepository.findOne(user.getId()).getPassword()) {
            throw new MyException("修改用户信息失败！");
        }
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

    /**
     * 用户充值钱包
     * @param userId
     * @param price
     */
    @Transactional
    public TransactionDetails recharge(Integer userId, double price) {
        User user = userRepository.findOne(userId);
        user.setPurse(user.getPurse() + price);
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        transactionDetails.setSum(price);
        transactionDetails.setUserId(userId);
        transactionDetails.setType(TransactionDetails.CHANGE_INTO);
        transactionDetails.setDescText("充值钱包" + transactionDetails.getSum() + "元");
        userRepository.save(user);
        return transactionDetailsRepository.save(transactionDetails);
    }

    /**
     * 用户提现
     * @param userId
     * @param price
     */
    @Transactional
    public TransactionDetails withdrawals(Integer userId, double price) {
        User user = userRepository.findOne(userId);
        if (user.getPurse() < price) {
            throw new MyException("余额不足，提现失败！");
        }
        user.setPurse(user.getPurse() - price);
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        transactionDetails.setSum(price);
        transactionDetails.setUserId(userId);
        transactionDetails.setType(TransactionDetails.WITHDRAWALS);
        transactionDetails.setDescText("提现" + transactionDetails.getSum() + "元");
        userRepository.save(user);
        return transactionDetailsRepository.save(transactionDetails);
    }

    /**
     * 分页查找交易明细
     * @param userId
     * @param pageNum
     * @param type
     * @return
     */
    public Page<TransactionDetails> findAllTransactionDetial (Integer userId, Integer type, Integer pageNum) {
        Sort sort =  new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNum - 1, ONE_PAGE_SUM , sort);
        Specification<TransactionDetails> specification = new Specification<TransactionDetails>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list=new ArrayList<Predicate>();
                if (type != null) {
                    list.add(criteriaBuilder.equal(root.get("type").as(Integer.class), type));
                }
                if (userId != null) {
                    list.add(criteriaBuilder.equal(root.get("userId").as(Integer.class), userId));
                }
                criteriaQuery.where(criteriaBuilder.and(list.toArray(new Predicate[list.size()])));
                criteriaQuery.distinct(false);
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id").as(Integer.class)));
                return criteriaQuery.getRestriction();
            }
        };
        return transactionDetailsRepository.findAll(specification, pageable);
    }

}
