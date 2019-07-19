package com.ivvysoft.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ivvysoft.cms.model.User;
import com.ivvysoft.cms.services.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String showMainPage() {
		return "users/login-form";
	}
	
	@GetMapping("/showFormForCreatingUser")
	public String showFormForAdd(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "users/user-creating-form";
	}
	
	@GetMapping("/save")
	public String saveUser(@RequestParam ("userName") final String userName, @RequestParam ("password") final String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(passwordEncoder().encode(password));
		userService.save(user);
		return "redirect:/login?saveUser=success";
		
	}
	
	/*
	 * @PostMapping("/checkLogin") public String checkLogin(@RequestParam
	 * ("userName") final String userName, @RequestParam ("password") final String
	 * password) { userDetailsServiceImpl.loadUserByUsername(userName); // User user
	 * = new User(); // user.setUserName(userName); //
	 * user.setPassword(passwordEncoder().encode(password)); //
	 * userService.save(user); return "persons/list-persons";
	 * 
	 * }
	 */
	
	
	
	
}