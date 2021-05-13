package com.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String email;
	private String password;
	private Boolean isAdmin;
}
