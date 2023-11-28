package com.jalba.proyecto_metamask_back.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String Test() {
        return "Hello World";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/verifytoken")
    public void verifyToken() {

    }

}
