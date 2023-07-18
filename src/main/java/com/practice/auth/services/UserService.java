package com.practice.auth.services;

import com.practice.auth.entities.User;
import com.practice.auth.repositories.UserRepository;
import com.practice.auth.security.JWT;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private JWT jwt;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        System.out.println(user);
        user.setPassword(BCrypt.hashpw(user.getPassword(),  BCrypt.gensalt()));
        return userRepository.save(user);
    }

    public String login(User user) {
        String stored_password = userRepository.findByUsername(user.getUsername()).getPassword();
        boolean password_verified = false;

        if (null == stored_password || !stored_password.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(user.getPassword(), stored_password);

        if(!password_verified){
            throw new java.lang.IllegalArgumentException("Invalid Password");
        }
        return jwt.generateJWT(user);
    }
}
