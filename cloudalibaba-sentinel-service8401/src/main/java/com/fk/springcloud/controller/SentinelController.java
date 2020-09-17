package com.fk.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @GetMapping("/testA")
    public String testA() {
        return "test A";
    }

    @GetMapping("/testB")
    public String testB() {
        return "test B";
    }
}
