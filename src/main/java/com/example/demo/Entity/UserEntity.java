package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",unique = true,nullable = false)
    private int id;

    @Column(name="email")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name="password",nullable = false)
    @Size(min=6,message = "Password must be at least 6 characters long")
    private String password;

    @Column(name="confirm_Password",nullable = false)
    @Size(min=6,message = "Password must be at least 6 characters long")
    private  String confirmPassword;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="age",nullable = false)
    private int age;

    @Column(name="weight",nullable = false)
    private  int weigth;

    @Column(name="target_Weight",nullable = false)
    private int targetWeight;

    @Column(name="height",nullable = false)
    private int height;

    @Column(name="role")
    private Role role;


}
