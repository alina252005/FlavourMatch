package com.example.demo.Repository;

import com.example.demo.Entity.SavedRecipesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedRecipesRepository extends  JpaRepository<SavedRecipesEntity,Integer>
{
}
