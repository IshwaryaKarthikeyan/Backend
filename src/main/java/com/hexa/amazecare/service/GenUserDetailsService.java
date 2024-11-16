package com.hexa.amazecare.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexa.amazecare.entity.GenUser;
import com.hexa.amazecare.entity.GenUserPrincipal;
import com.hexa.amazecare.exception.UserNotFoundException;
import com.hexa.amazecare.repository.GenUserRepository;

@Service
public class GenUserDetailsService implements UserDetailsService {
	
	@Autowired
	private GenUserRepository genUserRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		GenUser user = genUserRepo.findByEmail(email);
		
		if(user == null) {
			throw new UserNotFoundException("User Not Found");
		}
		
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase());
		
		return new GenUserPrincipal(user,Collections.singleton(authority));
	}
	
}
