package com.spring.SpringBoot_RestAPI.service;

import com.spring.SpringBoot_RestAPI.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QuickService {

    public boolean registerItem(ItemDto itemDto) {
        log.info("service...");
        return true;
    }
}
