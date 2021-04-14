package com.skc.task;

import com.skc.task.model.User;
import com.skc.task.repository.UserRepository;
import com.skc.task.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
