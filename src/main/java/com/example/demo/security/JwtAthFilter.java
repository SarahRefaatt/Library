package com.example.demo.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import dao.UserDao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAthFilter extends OncePerRequestFilter {
	
	
	private final UserDao userDao = new UserDao();
	private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {	
		
		
		
		final String authHeader=request.getHeader(ALREADY_FILTERED_SUFFIX);
		
		final String userEmail;
		final String jwtToken;
		
		
		if(authHeader==null || !authHeader.startsWith("Bearer")) {
			
			filterChain.doFilter(request, response);
			return ;
		}
		
		jwtToken=authHeader.substring(7);
		userEmail=jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		if (userEmail !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=userDao.findUserByEmail(userEmail);
			
			
			if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken authToken=
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}
	
	
	

}
