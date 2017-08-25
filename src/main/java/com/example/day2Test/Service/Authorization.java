package com.example.day2Test.Service;

import com.example.day2Test.Domains.Login;
import com.example.day2Test.Repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.NotSupportedException;

@Service
public class Authorization {
    @Autowired
    private LoginRepository loginRepository;

    public Boolean checkAuthorization(String requiredRole,HttpServletRequest request) {
        String rawCookie = request.getHeader("Cookie");
        rawCookie=rawCookie.replace("SESSIONID:","");
        Login login = loginRepository.getByCookie(rawCookie);
        String role = login.getUsers().getRole();

        if (!role.equals(requiredRole) || role.isEmpty()) {
           return false;
        }
        return true;
    }
}