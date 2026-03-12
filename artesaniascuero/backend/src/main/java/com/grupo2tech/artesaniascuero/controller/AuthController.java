package com.grupo2tech.artesaniascuero.controller;

import com.grupo2tech.artesaniascuero.model.User;
import com.grupo2tech.artesaniascuero.repository.UserRepository;
import com.grupo2tech.artesaniascuero.security.JwtService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository, JwtService jwtService){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User loginData){

        User user = userRepository.findByUsername(loginData.getUsername());

        if(user != null && user.isEnabled() && user.getPassword().equals(loginData.getPassword())){

            Map<String, Object> response = new HashMap<>();
            String token = jwtService.generateToken(user.getUsername(), user.getRole());

            response.put("token", token);
            response.put("username", user.getUsername());
            response.put("role", user.getRole());

            return response;
        }

        throw new RuntimeException("Credenciales incorrectas");
    }
}