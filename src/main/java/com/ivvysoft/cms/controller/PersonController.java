package com.ivvysoft.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ivvysoft.cms.model.Person;
import com.ivvysoft.cms.model.User;
import com.ivvysoft.cms.services.EmailSenderService;
import com.ivvysoft.cms.services.PersonService;
import com.ivvysoft.cms.services.UserService;

@Controller
@RequestMapping("/persons")
public class PersonController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private PersonService personService;

	@Autowired
	private EmailSenderService emailSenderService;

	@GetMapping("/list")
	public String listPersons(Model model) {
		User user = userService.findByUserName(userService.currentUser());
		List<Person> persons = personService.findByUserIdIn(user.getId());
		model.addAttribute("persons", persons);
		return "persons/list-persons";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "persons/person-form";
	}

	@GetMapping("/showFormForSearch")
	public String showFormForSearch() {
		return "persons/person-search-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("personId") int id, Model model) {	
		Person person = personService.findById(id);
		model.addAttribute("person", person);
		return "persons/person-form";

	}

	@PostMapping("/save")
	public String savePerson(@ModelAttribute("person") Person person) {
		User user = userService.findByUserName(userService.currentUser());
		person.setUser(user);
		personService.save(person);
		return "redirect:/persons/list";
	}

	@GetMapping("/delete")
	public String deleteById(@RequestParam("personId") int id) {
		personService.delete(id);
		return "redirect:/persons/list";
	}

	@PostMapping("/findByName")
	public String findByName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			Model model) {
		User user = userService.findByUserName(userService.currentUser());
		List<Person> persons = personService.findByName(firstName, lastName, user.getId());
		model.addAttribute("persons", persons);
		return "persons/list-persons";
	}

	@GetMapping("/showFormForSendingToEmail")
	public String showFormForSendingToEmail(Model model) {
		User user = userService.findByUserName(userService.currentUser());
		List<Person> persons = personService.findByUserIdIn(user.getId());
		model.addAttribute("persons", persons);
		return "persons/person-send-email-form";
	}

	@PostMapping("/sendEmail")
	public String sendEmail(@RequestParam("checkboxName[]") int[] checkboxValue,
			@RequestParam("emailAddress") String emailAddress, Model model) {

		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < checkboxValue.length; i++) {
			persons.add(personService.findById(checkboxValue[i]));
		}
		emailSenderService.sendEmail(emailAddress, persons);
		return "redirect:/persons/list?q=success";
	}
}
