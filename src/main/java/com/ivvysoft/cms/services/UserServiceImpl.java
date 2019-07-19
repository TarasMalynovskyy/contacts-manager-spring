package com.ivvysoft.cms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ivvysoft.cms.model.User;
import com.ivvysoft.cms.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void save(final User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUserName(final String username) {
        return userRepository.findByUserName(username);
    }

	@Override
	public String currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}