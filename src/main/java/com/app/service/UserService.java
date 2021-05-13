package com.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.app.repository.ArticleRepository;
import com.app.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.UserDTO;
import com.app.dto.UserLoginDTO;
import com.app.dto.UserRegistrationDTO;
import com.app.dto.UserWithTokenDTO;
import com.app.exceptions.NotFoundException;
import com.app.models.User;
import com.app.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostService postService;

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private final String myJWTSecret = "myJWTSecret";
	
	public List<UserDTO> getAllUsers(){
		List<UserDTO> users = new ArrayList<>();
		userRepository.findAll().stream().forEach((user) -> users.add(modelMapper.map(user, UserDTO.class)));;
		return users;				
	}
	
	public void signUpUser(UserRegistrationDTO registration) {
		boolean exists = userRepository
				.findByEmail(registration.getEmail())
				.isPresent();
		
		if(exists) {
			throw new IllegalStateException("email already taken");
		}
		
		String encodedPassword = bCryptPasswordEncoder
				.encode(registration.getPassword());
		
		registration.setPassword(encodedPassword);
		User userToSave = modelMapper.map(registration, User.class);
		userRepository.save(userToSave);
	}
	
	public UserWithTokenDTO loginUser(UserLoginDTO login) {
		Optional<User> userToLog = userRepository.findByEmail(login.getEmail());
		if(!userToLog.isPresent()) {
			throw new NotFoundException("User with email doesn't exist");
		}
		
		User userLogged = modelMapper.map(userToLog.get(), User.class);
		boolean isValidPassword = BCrypt.checkpw(login.getPassword(), userLogged.getPassword());
		if(!isValidPassword) {
			throw new NotFoundException("Password is not correct!");
		}
		UserDTO userDTO = modelMapper.map(userLogged, UserDTO.class);
		String tokenGenerated = getJWTToken(userDTO.getEmail());
		
		UserWithTokenDTO userToken = new UserWithTokenDTO(userDTO.getEmail(), tokenGenerated);
		return userToken;	
	}
	
	private String getJWTToken(final String email) {
		List<GrantedAuthority> granted = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		String token = Jwts.builder()
				.setId("bloggoJWT")
				.setSubject(email)
				.claim("authorities", granted.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, myJWTSecret.getBytes()).compact();
		
		return "Bearer " + token;
	}

	public void deleteUser(final Long userId){
		Optional<User> userToDelete = userRepository.findById(userId);
		if(!userToDelete.isPresent()){
			throw new NotFoundException("User doesn't exist!");
		}
		User user = userToDelete.get();
		postService.deleteByUserId(user.getUserId());
		articleService.deleteByUserId(user.getUserId());
		userRepository.delete(user);

	}
}
