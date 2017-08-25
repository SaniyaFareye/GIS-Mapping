package com.example.day2Test.Service;

import com.example.day2Test.DTO.LoginDTO;
import com.example.day2Test.Domains.Login;
import com.example.day2Test.Domains.Users;
import com.example.day2Test.Repositories.LoginRepository;
import com.example.day2Test.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserRepository userRepository;



    public LoginDTO authenticate(String username,String password){
        Users users=userRepository.findByUsername(username);
        if(users==null)
            throw new NoSuchElementException("username not valid");
        if(password.equals(users.getPassword())){
            Login login=new Login();
            login.setUsers(users);
            String uuid = UUID.randomUUID().toString();
            login.setCookie(uuid);
            loginRepository.save(login);

            LoginDTO loginDTO=new LoginDTO();
            loginDTO.setCookie(uuid);
            loginDTO.setUsername(users.getUsername());
            loginDTO.setRole(users.getRole());
            return loginDTO;
        }
        return null;
    }

    public LoginDTO getCookie(String cookie){
        Login login=loginRepository.getByCookie(cookie);
        if(login != null && login.getCookie().equals(cookie)){
            LoginDTO loginDTO=new LoginDTO();

            loginDTO.setUsername(login.getUsers().getUsername());
            loginDTO.setRole(login.getUsers().getRole());
            return loginDTO;
        }
        return null;
    }

    public void deleteCookie(String cookie){
        if(cookie!=null){
            try {
                loginRepository.deleteByCookie(cookie);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }
}
