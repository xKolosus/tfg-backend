package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserRegistrationDTO;
import com.app.service.RegisterService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private RegisterService registerService;

	@PostMapping
	public void register(@RequestBody UserRegistrationDTO request) {
		registerService.register(request);
	}
	
}
