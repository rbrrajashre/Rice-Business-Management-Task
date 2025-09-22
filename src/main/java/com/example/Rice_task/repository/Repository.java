package com.example.Rice_task.repository;

import com.example.Rice_task.entity.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

@org.springframework.stereotype.Repository


public interface Repository extends JpaRepository<Entity,Long>{

    List<Entity> findByLocalDate(LocalDate localDate);
    List<Entity> findByType(String type);
}






