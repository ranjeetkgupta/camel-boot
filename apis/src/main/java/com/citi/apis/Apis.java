package com.citi.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citi.domain.Account;
import com.citi.domain.Input;
import com.citi.domain.User;

@RestController
public class Apis {

	
	@PostMapping(value = "/user", produces = { "application/xml" }, consumes = { "application/xml" })
	public User getUserDetails(@RequestBody Input in) {

		User user = new User("raghvendra", "srinivas", "IN", "PRIVILAGED");
		return user;

	}
	
	
	@PostMapping(value = "/account", produces = { "application/xml" }, consumes = { "application/xml" })
	public Account getAccountDetails(@RequestBody Input in) {

		Account acc = new Account("1000 Rs", "596 Rs", "98 Rs");
		return acc;

	}
	
}
