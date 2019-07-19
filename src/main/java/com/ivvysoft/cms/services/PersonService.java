package com.ivvysoft.cms.services;

import java.util.List;

import com.ivvysoft.cms.model.Person;

public interface PersonService {

	public List<Person> findAll();

	public Person save(final Person person);

	public void delete(final int id);
	
	public Person findById(int id);
	
	public List<Person> findByName(final String firstName, final String lastName, final int userId);
	
	public List<Person> findByUserIdIn(final int userId);
	
}
