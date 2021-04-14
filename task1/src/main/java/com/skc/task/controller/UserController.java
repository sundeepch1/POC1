package com.skc.task.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skc.task.model.User;
import com.skc.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("saveUser")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
        user.setStatus(true);
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/getUserByNameSurnamePinCode")
    public ResponseEntity<List<User>> getUserByNameSurnamePinCode(@RequestBody String data){
        List<User> users = new ArrayList<>();
        if(!data.isEmpty()){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> jsonMap = objectMapper.readValue(data,
                        new TypeReference<Map<String,Object>>(){});
                users = userService.getUserByNameSurnamePinCode(jsonMap.get("name").toString(), jsonMap.get("surName").toString(), (Integer) jsonMap.get("pinCode"));
                return new ResponseEntity<List<User>>(users, HttpStatus.OK);
            }catch (Exception e){

            }
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getUserByDOBJoiningDateOnSort")
    public ResponseEntity<List<User>> getUserByDOBJoiningDateOnSort(){
        List<User> users = this.userService.getUserByDOBJoiningDateOnSort();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @DeleteMapping("/hardDelete/{userId}")
    public ResponseEntity<String> hardDeleteUser(@PathVariable("userId") Long userId){
        String status = userService.hardDeleteUser(userId, true);
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }

    @DeleteMapping("/softDelete/{userId}")
    public ResponseEntity<String> softDeleteUser(@PathVariable("userId") Long userId){
        String status = userService.hardDeleteUser(userId, false);
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        return "Checked.";
    }
}
