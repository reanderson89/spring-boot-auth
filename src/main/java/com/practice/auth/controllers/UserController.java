package com.practice.auth.controllers;

import com.practice.auth.entities.User;
import com.practice.auth.security.JWT;
import com.practice.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody User user){
        HttpHeaders responseHeaders = new HttpHeaders();
        String jwt = userService.login(user);
        responseHeaders.set("Authorization", jwt);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("User authenticated");
    }

    @GetMapping("/api/hello")
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("You can only see this if you are logged in!");
    }


}
