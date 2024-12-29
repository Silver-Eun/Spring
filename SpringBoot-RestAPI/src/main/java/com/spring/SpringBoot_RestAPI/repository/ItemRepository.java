package com.spring.SpringBoot_RestAPI.repository;

import com.spring.SpringBoot_RestAPI.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, String> {
}
