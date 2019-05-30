package com.crmpetproject.crmpetproject.security.service;


import com.crmpetproject.crmpetproject.models.User;
import com.crmpetproject.crmpetproject.servives.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

	private final UserService userService;

	@Autowired
	public AuthenticationService(UserService userService) {
		this.userService = userService;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByEmailOrPhone(username, username);
		if (user == null) {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		return user;
	}
}
