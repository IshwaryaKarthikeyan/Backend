package com.hexa.amazecare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
	
	@GetMapping("/")	
	public String greet() {
		return "Default Page";
	}

}
