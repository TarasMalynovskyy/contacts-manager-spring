package com.ivvysoft.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivvysoft.cms.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{
	
	public List<Person> findByUserIdAndFirstNameOrLastName(final int userId, final String firstName, final String lastName);
	
	public List<Person> findByUserIdIn(final int userId);
	
}
