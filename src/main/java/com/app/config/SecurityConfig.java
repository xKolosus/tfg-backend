package com.app.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.app.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private String[] urlGet = {
		"/posts", "/posts/*","/articles","/articles/*","/categories","/categories/*","/users","/users/*"	
	};
	
	private String[] urlPostLog = {
			"/login", "/login/*", "/register", "/register/*"
	};
	
	private String[] urlPostAuth = {
			"/posts", "/posts/*","/articles","/articles/*","/categories","/categories/*","/users","/users/*", "/articles/**", "/articles/posts/*"
	};
	
	private String[] urlPutAuth = {
		"/articles", "/articles/*"
	};
	
	private String[] urlDeleteAuth = {
		"/articles/*","/posts/*", "/users/*"	
	};
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors().disable();
    	http.csrf().disable()
		.addFilterAfter(new JWTFilterAuth(), UsernamePasswordAuthenticationFilter.class)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
			.antMatchers(HttpMethod.POST, urlPostLog).permitAll()
			.antMatchers(HttpMethod.GET, urlGet).permitAll()
		.and()
    		.exceptionHandling()
    		.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    	http.authorizeRequests()
    	.antMatchers(HttpMethod.POST, urlPostAuth).authenticated()
    	.antMatchers(HttpMethod.PUT, urlPutAuth).authenticated()
    	.antMatchers(HttpMethod.DELETE, urlDeleteAuth).authenticated();
     	    
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*", "http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
}

