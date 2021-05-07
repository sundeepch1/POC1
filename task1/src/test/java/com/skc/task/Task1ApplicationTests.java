package com.skc.task;

import com.skc.task.model.User;
import com.skc.task.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Task1ApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void getAllTest(){
        List<User> users = userRepository.findAll();

        System.out.println("Size >>>> " + users.size());
    }

}
