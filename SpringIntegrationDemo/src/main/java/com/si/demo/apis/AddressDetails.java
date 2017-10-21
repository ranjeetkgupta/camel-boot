package com.si.demo.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressDetails {

	
	@GetMapping("/address")
	public String getUserDetails()
	{
		System.out.println("get user address + invoked>>");
		return "mahadevpura bangalore";
		
	}
	
}
