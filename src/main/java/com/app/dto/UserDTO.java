package com.app.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String email;
	private String password;
	private String name;
	private String surname;
	private Boolean isAdmin;
	private String profilePicUrl;

	public UserDTO(String name, String surname, String email, String password) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}
	
	public UserDTO() {
	}
}
