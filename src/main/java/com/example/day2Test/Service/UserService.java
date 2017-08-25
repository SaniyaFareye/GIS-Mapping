package com.example.day2Test.Service;

import com.example.day2Test.Domains.Users;
import com.example.day2Test.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@Service
public class UserService  {
    @Autowired
    private  UserRepository userRepository;
    //Add User
    public Users addUser(Users users) throws SQLIntegrityConstraintViolationException,NoSuchElementException{
        if(users.getUsername().isEmpty() || users.getPassword().isEmpty())
            return null;
        if(!(users.getRole().equals("Data Analyst"))  &&  (!users.getRole().equals("Approver")))
            throw new NoSuchElementException("Role doesn't exist");


          Users user=userRepository.save(users);
        return user;
    }
}
