package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "JournalEntry")
public class JournalEntryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",unique = true,nullable = false)
    private  long id;

    @Enumerated(EnumType.STRING)
    @Column(name="mealType")
    private MealType mealType;

    @Column(name="calories")
    private Integer calories;

    @Column(name="proteins")
    private Integer proteins;

    @Column(name="fats")
    private Integer fats;

    @Column(name="carbs")
    private Integer carbs;

    @Column(name="quantity")
    private Double quantity;

    @Column(name="recipeId")
    private Integer recipeId;

    @Column(name="customFoodName")
    private String customFoodName;

    @Column(name="date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity user;


}
