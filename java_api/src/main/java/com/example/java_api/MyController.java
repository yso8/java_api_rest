package com.example.java_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
    @GetMapping("/get")
    public String get() {
        return "Get";
    }

    @GetMapping("/update")
    public String update() {
        return "Update";
    }

    @GetMapping("/delete")
    public String delete() {
        return "Delete";
    }
}
