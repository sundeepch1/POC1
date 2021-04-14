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

    public User saveUser(User user){
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
}
