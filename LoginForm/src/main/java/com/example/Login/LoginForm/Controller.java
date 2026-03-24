package com.example.Login.LoginForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/loginpage")
public class Controller {

    @Autowired
    private service ser;

    @PostMapping("/signup")
    public String signup(@RequestBody Signup sign){
        return ser.SignUp(sign);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginReq login){
        return ser.LOGIN(login);
    }
}
