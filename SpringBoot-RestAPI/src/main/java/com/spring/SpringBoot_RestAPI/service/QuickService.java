package com.spring.SpringBoot_RestAPI.service;

import com.spring.SpringBoot_RestAPI.dto.ItemDto;
import com.spring.SpringBoot_RestAPI.mapper.QuickMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class QuickService {

    @Autowired
    private QuickMapper quickMapper;

    public boolean registerItem(ItemDto itemDto) {

        HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("id", itemDto.getId());
        paramMap.put("name", itemDto.getName());

        quickMapper.registerItem(paramMap);
        return true;
    }

    public ItemDto getItemById(String id) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        HashMap<String, Object> res = quickMapper.findById(paramMap);

        ItemDto itemDto = new ItemDto();
        itemDto.setId((String) res.get("ID"));
        itemDto.setName((String) res.get("NAME"));

        return itemDto;
    }
}
