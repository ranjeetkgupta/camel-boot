package com.camel.boot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressDetails {

	
	@RequestMapping("/address")
	public String getUserDetails()
	{
		System.out.println("get user address + invoked>>");
		return "mahadevpura bangalore";
		
	}
	
}
