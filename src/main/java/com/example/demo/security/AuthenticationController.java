package com.example.demo.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;

import dao.UserDao;
import lombok.RequiredArgsConstructor;
import com.example.demo.security.RegisterRequest;
import com.example.demo.repository.UserReposetory;
import com.example.demo.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
	
	
	 	private UserReposetory userRepository;
	    private RoleRepository roleRepository;
	    private PasswordEncoder passwordEncoder;
	    
	    
	    
	  
	    
	    
	
	private final AuthenticationManager authenticationManger =new AuthenticationManager() {
		
		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	private final UserDao userDao = new UserDao();
	private final JwtTokenUtil jwatTokenUtils = new JwtTokenUtil();
	
	  @Autowired
	    public AuthenticationController ( UserReposetory userRepository,
	                          RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	        this.passwordEncoder = passwordEncoder;
	    }
	    
	
	
	
	
	@PostMapping("/token")
	public ResponseEntity<String> authenticate( @RequestBody AuthenticationRequest authenticationRequest){
		
		authenticationManger.authenticate(

				new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
				);
		
		final UserDetails user=userDao.findUserByEmail(authenticationRequest.getEmail());
		String  userName=user.getUsername();
		if (user!=null) {
			
			return ResponseEntity.ok(jwatTokenUtils.generateToken(userName));
			
		}
		
		
		
		return ResponseEntity.status(400).body("Some error occured");
		
	}
	
	
	
	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
	    if (userRepository.existsByName(registerDto.getName())) {
	        return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
	    }

	    User user = new User();
	    user.setName(registerDto.getName());
	    user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

	    Role role = roleRepository.findRoleByName("USER");
	    user.setRoles(Collections.singletonList(role));

	    userRepository.save(user);

	    return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
	}

	
	
	
	
	

	

}
