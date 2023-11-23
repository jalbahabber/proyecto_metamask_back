package com.jalba.proyecto_metamask_back.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping( "/prueba" )
	public String Login() {
		return "Hello World";
    }  

    
}
