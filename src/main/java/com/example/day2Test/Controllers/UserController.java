package com.example.day2Test.Controllers;

import com.example.day2Test.DTO.Status;
import com.example.day2Test.Domains.Users;
import com.example.day2Test.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity addTask(@RequestBody Users users){
        Users users1=new Users();

        if(users==null){
            return new ResponseEntity(new Status("Empty data"), HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            users1= userService.addUser(users);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity(new Status("Enter valid Role"),HttpStatus.CONFLICT);
        }
        catch (SQLIntegrityConstraintViolationException e){
            return  new ResponseEntity(new Status("Username should be unique"),HttpStatus.CONFLICT);
        }


        if(users1==null){
            return new ResponseEntity(new Status("Fill all the enteries"),HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(new Status("User Added"),HttpStatus.OK);
    }
}
