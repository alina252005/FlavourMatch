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
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="username",unique = true)
    private String username;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="age",nullable = false)
    private int age;

    @Column(name="weight",nullable = false)
    private  int weight;

    @Column(name="target_weight",nullable = false)
    private int targetWeight;

    @Column(name="height",nullable = false)
    private int height;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;


}
