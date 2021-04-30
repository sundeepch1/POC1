package com.skc.task.controller;

import com.skc.task.domain.Response;
import com.skc.task.model.User;
import com.skc.task.service.UserService;
import com.skc.task.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreLoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/api/registration")
    public ResponseEntity<Response> registration(@RequestBody User user){
        User userFromDB = userService.getUserByEmail(user.getEmailAccount());
        if(userFromDB == null) {
            user.setRole("USER");
            user.setPassword(PasswordUtil.getPasswordHash(user.getPassword()));
            User dbUser = userService.saveUser(user);
            if(dbUser != null) {
                return new ResponseEntity<Response>(new Response("User is saved successfully"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<Response>(new Response("email_exist"), HttpStatus.OK);
    }
}
