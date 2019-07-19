package com.ivvysoft.cms.services;

import java.util.List;

import com.ivvysoft.cms.model.Person;

public interface EmailSenderService {
	
	public void sendEmail(final String emailAddress, final List<Person> persons);

}
