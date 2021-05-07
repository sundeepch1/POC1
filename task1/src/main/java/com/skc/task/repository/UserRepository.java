package com.skc.task.repository;

import com.skc.task.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
public interface UserRepository extends MongoRepository<User, Long> {

    List<User> findByFirstNameAndSurNameAndPinCode(String name, String surname, Integer pinCode);

    User findByEmailAccountIgnoreCase(String username);
}
