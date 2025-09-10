package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="SavedRecipes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavedRecipesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

   @Column(name="external_id")
    private Integer externalId;

   @ManyToOne
   @JoinColumn(name="uuser_id")
    private UserEntity user;
   
   
   
   
   
   
   
   
   
   
   
   



}
