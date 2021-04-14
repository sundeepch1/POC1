package com.skc.task.controller;

import com.skc.task.model.User;
import com.skc.task.repository.UserRepository;
import com.skc.task.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void saveUser() {

    }

    @Test
    void getUserByNameSurnamePinCode() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("http://localhost:9090/getUserByDOBJoiningDateOnSort");
       // requestBuilder.
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
              //  .andExpect(MockMvcResultMatchers.content().json());
    }

    @Test
    void getUserByDOBJoiningDateOnSort() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:9090/getUserByDOBJoiningDateOnSort");
        MvcResult result =  mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        //  .andExpect(MockMvcResultMatchers.content().json());
        System.out.println("My result " + result.getResponse().getContentAsString());
    }

    @Test
    void hardDeleteUser() throws Exception {
        //when(userService.hardDeleteUser(3L, true)).thenReturn("User is deleted successfully.");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("http://localhost:9090/hardDelete/{userId}",3);
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.content().string("User is deleted successfully."));
                //.andExpect(MockMvcResultMatchers.content().string("No such user found."));
                // "User is deleted successfully.";

        List<User> users = this.userRepository.findAll();
        System.out.println("My Result >>>>  " + users.size());
    }

    @Test
    void softDeleteUser() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("http://localhost:9090/softDelete/{userId}",4);
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void test1() {
    }
}