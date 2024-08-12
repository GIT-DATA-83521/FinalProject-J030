package com.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	//dep : password encoder
	@Autowired
	private PasswordEncoder enc;
	//dep : custom jwt auth filter
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	//dep : custom auth entry point
	@Autowired
	private CustomAuthenticationEntryPoint authEntry;
	
	@Bean
	public SecurityFilterChain authorizeRequest(HttpSecurity http)throws Exception{
		//URL based authorization rules
		http
			.cors()
			.and()
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(authEntry)
			.and()
			.authorizeRequests()
			.antMatchers("/user/signup","/user/signin","/swagger-ui/**","/v*/api-doc*/**").permitAll()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.antMatchers("/posts/**").hasRole("BLOGGER")
			.antMatchers("posts/deletePost").hasRole("ADMIN")
			.antMatchers("/categories/addcategory","/categories/updateCat","/categories/updateCat").hasRole("ADMIN")
			.antMatchers("/comment/**").hasRole("COMMENTER")
			.anyRequest().authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager
	(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
}
