package com.skc.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skc.task.model.User;
import com.skc.task.repository.UserRepository;
import com.skc.task.service.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UserController.class)
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
class UserControllerTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    public void getAllTest(){
        List<User> users = userRepository.findAll();

        System.out.println("Size >>>> " + users.size());
    }

    @Test
    void saveUser() throws Exception {
        User user = new User();
        user.setFirstName("Sundeep");
        user.setSurName("Chaurasiya");
        user.setEmailAccount("sundeep.kumar@gmail.com");
        user.setPhoneNumber("9088776655");
        user.setDateOfBirth("1990-09-03");
        user.setJoiningDate("2021-04-01");
        user.setPinCode(110096);
        user.setLastCompanyName("Wheebox");
        user.setCurrentCompanyPackage(32.45F);
        user.setCurrentCompanyName("NeoSoft");
        user.setCurrentCompanyPackage(50.45F);
        user.setRelevantExperience(3.4F);
        user.setTotalExperience(3.4F);

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveUser")
                .content(str)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    void getUserByNameSurnamePinCode() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Sundeep");
        jsonObject.put("surName", "Chaurasiya");
        jsonObject.put("pinCode", 110096);
        String str = jsonObject.toString();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/getUserByDOBJoiningDateOnSort")
                .content(str)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void getUserByDOBJoiningDateOnSort() throws Exception {
        List<User> users = new ArrayList<>();
        Mockito.when(userService.getUserByDOBJoiningDateOnSort()).thenReturn(users);
        System.out.println("Users >>>> "+users);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getUserByDOBJoiningDateOnSort");
        MvcResult result =  mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        result.getResponse().getContentAsString();
    }

    @Test
    void hardDeleteUser() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/hardDelete/{userId}",3);
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User is deleted successfully."))
                .andExpect(MockMvcResultMatchers.content().string("No such user found."));

    }

    @Test
    void softDeleteUser() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/softDelete/{userId}",3);
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User is deleted successfully."))
                .andExpect(MockMvcResultMatchers.content().string("No such user found."));
    }

    @Test
    void test1() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/test");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        System.out.println("My test result >>> " + result.getResponse().getContentAsString());
    }
}