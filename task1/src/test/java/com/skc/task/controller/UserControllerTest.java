package com.skc.task.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skc.task.model.User;
import com.skc.task.repository.UserRepository;
import com.skc.task.service.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
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

    @Autowired
    ObjectMapper objectMapper;

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

        User user1 = new User();

        Mockito.when(userService.saveUser(user1)).thenReturn(user1);

        String str = objectMapper.writeValueAsString(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveUser").contentType(MediaType.APPLICATION_JSON)
                .content(str)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        //System.out.println(result.getResponse().getContentAsString());
        user = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
        Assertions.assertEquals(user1,user);
    }

    @Test
    void getUserByNameSurnamePinCode() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Sundeep");
        jsonObject.put("surName", "Chaurasiya");
        jsonObject.put("pinCode", 110096);
        String str = jsonObject.toString();

        List<User> users1 = new ArrayList<>();

        Mockito.when(userService.getUserByNameSurnamePinCode("Sundeep", "Chaurasiya", 110096)).thenReturn(users1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/getUserByDOBJoiningDateOnSort")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(str)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<User> users = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<User>>() {});

        Assertions.assertEquals(users, users1);
    }

    @Test
    void getUserByDOBJoiningDateOnSort() throws Exception {
        List<User> users = new ArrayList<>();
        Mockito.when(userService.getUserByDOBJoiningDateOnSort()).thenReturn(users);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getUserByDOBJoiningDateOnSort");
        MvcResult result =  mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        List<User> users1 = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<User>>() {});

        Assertions.assertEquals(users, users1);

    }

    @Test
    void hardDeleteUser() throws Exception {

        String result = "";

        Mockito.when(userService.hardDeleteUser(3L, true)).thenReturn(result);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/hardDelete/{userId}",3);
        MvcResult result1 = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String str = result1.getResponse().getContentAsString();
        Assertions.assertEquals(str, result);
    }

    @Test
    void softDeleteUser() throws Exception {
        String result = "";

        Mockito.when(userService.hardDeleteUser(5L, false)).thenReturn(result);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/hardDelete/{userId}",3);
        MvcResult result1 = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String str = result1.getResponse().getContentAsString();
        Assertions.assertEquals(str, result);
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