package com.will.blog.service.impl;

import com.will.blog.dao.UserDao;
import com.will.blog.entity.User;
import com.will.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User checkUser(String username, String password){

        //TODO 用户登录信息加盐
        User user = userDao.findByUsernameAndPassword(username,password);
        return user;
    }

    @Override
    public User get(Long id) {
        return userDao.getOne(id);
    }
}
