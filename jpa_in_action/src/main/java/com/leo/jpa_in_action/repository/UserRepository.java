package com.leo.jpa_in_action.repository;

import com.leo.jpa_in_action.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
