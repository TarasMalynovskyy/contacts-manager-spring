package com.ivvysoft.cms.services;

import java.util.List;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.stereotype.Service;

import com.ivvysoft.cms.model.Person;
import com.ivvysoft.cms.utils.PersonUtils;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	@Override
	public void sendEmail(final String emailAddress, final List<Person> persons) {

		Mailer mailer = MailerBuilder
				.withSMTPServer("smtp.gmail.com", 587, "com.ivvysoft.contactsmanager@gmail.com", "Contacts1408988")
				.buildMailer();

		Email email = EmailBuilder.startingBlank().from("Ivvy Soft", "com.ivvysoft.contactsmanager@gmail.com")
				.to(emailAddress).withSubject("Person(s) info avalible for you")
				.withPlainText(PersonUtils.personsToString(persons)).buildEmail();

		mailer.sendMail(email);

	}

}
