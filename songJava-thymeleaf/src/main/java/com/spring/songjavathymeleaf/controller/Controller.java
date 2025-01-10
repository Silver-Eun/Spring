package com.spring.songjavajpa.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/example1")
    public String example1(Model model){
        model.addAttribute("name", "name");
        model.addAttribute("values", Arrays.asList("spring", "springboot", "vue.js", "react"));
        return "/example1";
    }
}
