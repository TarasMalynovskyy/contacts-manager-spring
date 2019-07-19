package com.ivvysoft.cms.configuration;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ivvysoft.cms.model.User;
import com.ivvysoft.cms.services.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		try {
			final User user = userService.findByUserName(username);
			
			return org.springframework.security.core.userdetails.User.builder()
					.username(user.getUserName())
					.password(user.getPassword())
					.roles("USER")
						.build();
		} catch (final EntityNotFoundException e) {
			throw new UsernameNotFoundException(e.getLocalizedMessage(), e);
		}
	}
}
