package com.job.scrapper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/") public String health()
    {
        return "I am working fine";
    }
}
