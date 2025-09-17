package com.example.demo.DTO;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.*;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {
    @Email(message = "email should be valid")
    private String email;
    @Size(min=6,message = "Password must be at least 6 characters long")
    private  String password;
    @Size(min=3,message = "Username shoud be at least 3 charcates long")
    private String username;
    @Size(min=3,message = "First Name shoud be at least 3 charcates long")
    private String firstName;
    @Size(min=3,message = "First Name shoud be at least 3 charcates long")
    private String lastName;
     private int age;
     private int weigth;
     private int targetWeight;
     private int height;
     private Role role;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(email)
                .password(password)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .weight(weigth)
                .targetWeight(targetWeight)
                .height(height)
                .role(role)
                .build();
    }



}
