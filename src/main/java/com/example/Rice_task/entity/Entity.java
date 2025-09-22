package com.example.Rice_task.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@jakarta.persistence.Entity
@Table(name="businesses")
public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate localDate;

    private String type;

    private Double price ;

    private Double quantity;

    private String location;

}





