package com.app.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDTO;
import com.app.dto.UserNoPasswdDTO;
import com.app.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	@Produces("application/json")
	public @ResponseBody List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}

	@DeleteMapping("{userId}")
	@Produces("application/json")
	public void deleteUser(@PathVariable final Long userId){
		userService.deleteUser(userId);
	}
	
	@GetMapping("/byEmail")
	@Produces("application/json")
	public @ResponseBody UserNoPasswdDTO getUserByEmail(@QueryParam("email") final String email) {
		return userService.getUserByEmail(email);
	}
	
	@GetMapping("{userId}")
	public @ResponseBody UserDTO getUserById(@PathVariable final Long userId) {
		return userService.getUserByID(userId);
	}
	
	@PutMapping("{userId}")
	public @ResponseBody UserNoPasswdDTO updateUser(@PathVariable final Long userId, @RequestBody final UserNoPasswdDTO userInfo) {
		return userService.updateUser(userId, userInfo);
	}
	
	@GetMapping("/posts/count/{userId}")
	public @ResponseBody Integer countPostsByUserId(@PathVariable final Long userId) {
		return userService.countPostsByUserId(userId);
	}
}
