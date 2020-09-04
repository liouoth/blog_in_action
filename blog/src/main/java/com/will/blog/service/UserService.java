package com.will.blog.service;

import com.will.blog.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User checkUser(String userName, String userPassword);
}
