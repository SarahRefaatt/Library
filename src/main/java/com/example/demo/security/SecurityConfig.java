package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dao.UserDao;



import java.util.Collections;
import java.util.List;

import java.util.Arrays; // Import the correct Arrays class here

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
    private final JwtAthFilter jwtAuthFilter = new JwtAthFilter();
    
    private final UserDao userDao = new UserDao();

    /*
    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
        new User("sarah", "sarah", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
        new User("user", "user", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
    );*/
    		
	@Bean
	public InMemoryUserDetailsManager user() {
		return new InMemoryUserDetailsManager(
			User.withUsername("user")
				.password("{noop}user")
				.authorities("read")
				.build()
		);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		HttpSecurity httpSecurity = http
				.csrf().disable()
				.authorizeRequests()
				//.requestMatchers("/api/**").permitAll()

				.requestMatchers("/api/**").permitAll()
				.requestMatchers("/user/**").permitAll()
				.requestMatchers("/book/**").permitAll()
				.requestMatchers("/borrow/**").permitAll()

				.requestMatchers("/patron/**").permitAll()

				.anyRequest()

				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(AuthenticationProvider())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	@Bean
	public AuthenticationProvider AuthenticationProvider() {
		
		final DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		
		authenticationProvider.setPasswordEncoder(PasswordEncoder());
		
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager(); 
	}
	

	@Bean
	public PasswordEncoder PasswordEncoder() {
		
		//return new BCryptPasswordEncoder();
		
		return NoOpPasswordEncoder.getInstance();
		
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				return userDao.findUserByEmail(email);
			}
		};
	}	
}