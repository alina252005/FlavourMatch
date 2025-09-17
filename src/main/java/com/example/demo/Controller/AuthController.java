package com.example.demo.Controller;

import com.example.demo.DTO.AuthRequest;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Security.JwtUtil;
import com.example.demo.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtils;
    @PostMapping("/signin")
    public String authenticateUser(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }
    @PostMapping("/signup")
    public String registerUser(@RequestBody UserEntity user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            // Check if the username is already taken
            return "Error: Username is already taken!";
        }
        if (user.getPassword() == null) {
            // Check if password or confirm password is null
            return "Error: Password and Confirm Password cannot be null!";
        }

        if(user.getPassword().length()<6)
        {

            return "Error: Password must be at least 6 characters long!";
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            // Check if the email is already taken
            return "Error: Email is already in use!";
        }
        // Create new user's account
        UserEntity newUser = new UserEntity();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setAge(user.getAge());

        newUser.setEmail(user.getEmail());
        newUser.setRole(user.getRole());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setHeight(user.getHeight());
        newUser.setWeight(user.getWeight());
        newUser.setTargetWeight(user.getTargetWeight());
        userRepository.save(newUser);
        return "User registered successfully!";
    }
}