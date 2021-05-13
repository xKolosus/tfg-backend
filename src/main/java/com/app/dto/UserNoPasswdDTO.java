package com.app.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserNoPasswdDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long userId;
	private String email;
	private String name;
	private String surname;
	private Boolean isAdmin;
	private String profilePicUrl;
}
