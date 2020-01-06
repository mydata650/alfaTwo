package com.alfaTwo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OneController {
	@GetMapping(path="/hello")
	public String SayHello() {
		return "Hellow World";
	}
}
