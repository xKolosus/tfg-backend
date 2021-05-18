package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserLoginDTO;
import com.app.dto.UserWithTokenDTO;
import com.app.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping
	public @ResponseBody UserWithTokenDTO login(@RequestBody final UserLoginDTO userLogin) {
		UserWithTokenDTO userLogged = userService.loginUser(userLogin);
		return userLogged;
	}
}
