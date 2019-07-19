package com.ivvysoft.cms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivvysoft.cms.model.Person;
import com.ivvysoft.cms.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public Person save(Person person) {
		return personRepository.save(person);
	}

	@Override
	public void delete(int id) {
		personRepository.deleteById(id);
	}

	@Override
	public Person findById(int id) {
		Optional<Person> result = personRepository.findById(id);
		Person person = null;

		if (result.isPresent()) {
			person = result.get();
		} else {
			throw new RuntimeException("Did not find employee id - " + id);
		}

		return person;
	}

	@Override
	public List<Person> findByName(final String firstName, final String lastName, final int userId) {
		return personRepository.findByUserIdAndFirstNameOrLastName(userId, firstName, lastName);
	}

	@Override
	public List<Person> findByUserIdIn(int userId) {
		List<Person> persons = personRepository.findByUserIdIn(userId);
		return persons;
	}

}
