package com.skc.task.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skc.task.model.User;
import com.skc.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String name) {
        return userRepository.findByEmailAccountIgnoreCase(name);
    }

    public User saveUser(User user){
        long id = user.getId();
        if(id != 0){
            Optional<User> saUser= userRepository.findById(id);
            User actualUser = saUser.get();
            user.setPassword(actualUser.getPassword());
        }
        return userRepository.save(user);
    }

    public User saveMongoUser(User user){
/*        long id = user.getId();
        if(id != 0){
            Optional<User> saUser= userRepository.findById(id);
            User actualUser = saUser.get();
            user.setPassword(actualUser.getPassword());
        }*/
        return userRepository.save(user);
    }

    public List<User> getUserByNameSurnamePinCode(String name, String surname, Integer pinCode){
        return userRepository.findByFirstNameAndSurNameAndPinCode(name, surname, pinCode);
    }

    public List<User> getUserByDOBJoiningDateOnSort(){
        return  userRepository.findAll(Sort.by("dateOfBirth").and(Sort.by("joiningDate")));
    }

    public String hardDeleteUser(Long userId, Boolean isHardDelete){
        Optional user = userRepository.findById(userId);
        if(user.isPresent()){
            User findUser = (User)user.get();
            if(isHardDelete){
                userRepository.delete(findUser);
                return "User is deleted successfully.";
            }else{
                findUser.setStatus(false);
                userRepository.save(findUser);
                return "User is deleted successfully.";
            }
        }
        return "No such user found.";
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long userId){
        Optional<User> user = this.userRepository.findById(userId);
        User user1 = null;
        if(user.isPresent()){
            user1 = (User)user.get();
        }else {
            return null;
        }
        return user1;
    }

    public String test1(){
        return "checked";
    }
}
