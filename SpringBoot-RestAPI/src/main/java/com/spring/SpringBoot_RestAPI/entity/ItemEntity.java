package com.spring.SpringBoot_RestAPI.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ITEM")
public class ItemEntity {
    @Id
    private String id;
    
    private String name;

}
