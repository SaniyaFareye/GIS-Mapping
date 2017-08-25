package com.example.day2Test.Controllers;

import com.example.day2Test.DTO.LoginDTO;
import com.example.day2Test.DTO.Status;
import com.example.day2Test.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    public LoginService loginService;

    @RequestMapping(value = "/authenticate",method = RequestMethod.GET)
    public ResponseEntity authentictate(@Param("username") String username, @Param("password")String password,
                                        HttpServletResponse response){
        LoginDTO loginDTO;
        if(username.isEmpty() || password.isEmpty()){
            return new ResponseEntity(new Status("Empty data"), HttpStatus.UNAUTHORIZED);
        }
        try {
            loginDTO = loginService.authenticate(username, password);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity(new Status("Enter valid Username"),HttpStatus.UNAUTHORIZED);
        }
        if(loginDTO==null) {
            return new ResponseEntity(new Status("Enter Valid Credentials"), HttpStatus.UNAUTHORIZED);
        }
        response.setHeader("Set-Cookie","SESSIONID:"+loginDTO.getCookie() + ";Path=/;");
        loginDTO.setCookie(null);
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/session",method = RequestMethod.GET)
    public ResponseEntity checkSession(HttpServletRequest request){
        //Cookie cook = WebUtils.getCookie(request,"SESSIONID");
        String rawCookie = request.getHeader("Cookie");
        if(rawCookie == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        rawCookie=rawCookie.replace("SESSIONID:","");
       LoginDTO loginDTO = loginService.getCookie(rawCookie);
        if(loginDTO==null){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(loginDTO,HttpStatus.OK);
    }


    @RequestMapping(value = "/logout",method = RequestMethod.DELETE)
    public ResponseEntity deleteSession(HttpServletRequest request){
        String rawCookie = request.getHeader("Cookie");
        rawCookie=rawCookie.replace("SESSIONID:","");
        loginService.deleteCookie(rawCookie);

        return new ResponseEntity(HttpStatus.OK);
    }
}
