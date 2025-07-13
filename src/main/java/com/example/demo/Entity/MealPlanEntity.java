package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="MealPlan")
public class MealPlanEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="userid",unique = true,nullable = false)
    private int userid;
    @Column(name="recipeid",unique = true,nullable = false)
    private int recipeid;
    @Column(name="CreatedDate",nullable = false)
    private LocalDate createdDate;
    @Column(name ="isActive",nullable = false   )
    private boolean is_active;

}
