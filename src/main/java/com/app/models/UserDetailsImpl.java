package com.app.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private User user;
	
	private String name;
	private String email;
	private String password;
	private String surname;
	private Boolean isAdmin;
	private String profilePicUrl;
	
	public UserDetailsImpl(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.surname = user.getSurname();
		this.isAdmin = user.getIsAdmin();
		this.profilePicUrl = user.getProfilePicUrl();
		}
	
	public UserDetailsImpl() {
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
