package com.spring.songjava.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/example/parameter")
public class ExampleParameterController {

    Logger logger = LoggerFactory.getLogger(getClass());

    // RequsetParam
    @GetMapping("/example1")
    public void example1(@RequestParam String id,
                         @RequestParam String code,
                         Model model) {
        model.addAttribute("id", id);
        model.addAttribute("code", code);
    }

    // RequsetParam with Map
    @GetMapping("/example2")
    public void example2(@RequestParam Map<String, Object> paramMap,
                         Model model) {
        model.addAttribute("paramMap", paramMap);
    }

    // Class
    @GetMapping("/example3")
    public void example3(ExampleParameter parameter,
                         Model model) {
        model.addAttribute("parameter", parameter);
    }

    // PathVariable
    @GetMapping("/example4/{id}/{code}")
    public String example4(@PathVariable String id,
                           @PathVariable String code,
                           Model model) {
        model.addAttribute("id", id);
        model.addAttribute("code", code);

        return "/example/parameter/example4";
    }

    // String[]
    @GetMapping("/example5")
    public String example5(@RequestParam String[] ids,
                           Model model) {
        model.addAttribute("ids", ids);

        return "/example/parameter/example5";
    }

    // jQuery
    @GetMapping("/example6/form")
    public void form() {
    }

    @PostMapping("/example6/saveData")
    @ResponseBody
    public Map<String, Object> example6(@RequestBody ExampleRequestBodyUser requestBody,
                                        Model model) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", true);
        logger.info("requestBody : {}", requestBody);
        return resultMap;
    }
}
