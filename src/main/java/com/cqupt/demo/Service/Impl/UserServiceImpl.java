package com.cqupt.demo.Service.Impl;

import com.cqupt.demo.Bean.User;
import com.cqupt.demo.Dao.UserDao;
import com.cqupt.demo.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int addUser(User user) { return userDao.insertUser(user); }

    @Override
    public User queryUser(String userName) {
        return userDao.selectUser(userName);
    }
}
