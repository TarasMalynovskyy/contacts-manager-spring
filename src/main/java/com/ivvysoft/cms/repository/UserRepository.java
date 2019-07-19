package com.ivvysoft.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivvysoft.cms.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByUserName(final String userName);

}
