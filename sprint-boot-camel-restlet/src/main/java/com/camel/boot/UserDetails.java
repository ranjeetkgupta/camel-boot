package com.camel.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetails {

	
	@RequestMapping("/user")
	public String getUserDetails()
	{
		System.out.println("get user details invoked>>");
		return "emc user";
		
	}
	
}
