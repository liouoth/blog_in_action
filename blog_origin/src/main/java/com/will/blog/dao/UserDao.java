package com.will.blog.dao;

import com.will.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
}
