package com.si.demo.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetails {

	
	@GetMapping("/user")
	public String getUserDetails()
	{
		System.out.println("get user details invoked>>");
		return "emc user";
		
	}
	
}
