package com.example.Login.LoginForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class service {

    @Autowired
    private Repo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWT jwt;

    public String SignUp(Signup signup){
        if(signup.getUsername() == null || signup.getEmail() == null || signup.getPassword() == null){
            throw new RuntimeException("Input provided is not valid");
        }

        Info info = new Info();
        info.setUsername(signup.getUsername());
        info.setEmail(signup.getEmail());
        info.setPassword(passwordEncoder.encode(signup.getPassword()));

        repo.save(info);
        return "User Registered Successfully";
    }

    public String LOGIN(LoginReq log){
        Info info = repo.findById(log.getUsername())
                .orElseThrow(() -> new RuntimeException("User is Not Registered Yet"));

        if(!passwordEncoder.matches(log.getPassword(), info.getPassword())){
            throw new RuntimeException("Invalid Password Credentials");
        }

        return jwt.createToken(info.getUsername());
    }
}
