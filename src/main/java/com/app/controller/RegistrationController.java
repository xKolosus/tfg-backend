package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserRegistrationDTO;
import com.app.service.RegisterService;

@CrossOrigin("http://localhost:4200")
@RestController
public class RegistrationController {

	@Autowired
	private RegisterService registerService;

	@CrossOrigin("http://localhost:4200")
	@PostMapping("/register")
	public void register(@RequestBody UserRegistrationDTO request) {
		registerService.register(request);
	}
	
}
