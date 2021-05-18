package com.app.service;

import org.springframework.stereotype.Service;

import com.app.dto.UserRegistrationDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegisterService {
	
	private UserService userService;
	
	public void register(UserRegistrationDTO request) {
		userService.signUpUser(
				new UserRegistrationDTO(
						request.getName(),
						request.getSurname(),
						request.getEmail(),
						request.getPassword(), 
						request.getProfilePicUrl()
						)
				);
	}
}
