package com.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserWithTokenDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String email;
	private String bearerToken;
	private String profilePicUrl;

}
