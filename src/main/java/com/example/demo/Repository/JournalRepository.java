package com.example.demo.Repository;

import com.example.demo.Entity.JournalEntryEntity;
import com.example.demo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface JournalRepository extends JpaRepository<JournalEntryEntity, Long> {
    List<JournalEntryEntity> findByUserAndDate(UserEntity user, LocalDate date);
}
