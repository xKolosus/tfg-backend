package com.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
}
