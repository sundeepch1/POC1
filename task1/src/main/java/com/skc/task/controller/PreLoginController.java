package com.skc.task.controller;

import com.skc.task.domain.Response;
import com.skc.task.model.User;
import com.skc.task.service.SequenceGeneratorService;
import com.skc.task.service.UserService;
import com.skc.task.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.skc.task.model.User.SEQUENCE_NAME;

@RestController
public class PreLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @PostMapping(value="/api/registration")
    public ResponseEntity<Response> registration(@RequestBody User user){
        User userFromDB = userService.getUserByEmail(user.getEmailAccount());
        if(userFromDB == null) {
            user.setId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));
            user.setRole("USER");
            user.setStatus(true);
            user.setPassword(PasswordUtil.getPasswordHash(user.getPassword()));
            User dbUser = userService.saveMongoUser(user);
            if(dbUser != null) {
                return new ResponseEntity<Response>(new Response("User is saved successfully"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<Response>(new Response("email_exist"), HttpStatus.OK);
    }
}
