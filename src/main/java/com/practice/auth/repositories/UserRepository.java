package com.practice.auth.repositories;

import com.practice.auth.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
User findByUsername(String username);
}
