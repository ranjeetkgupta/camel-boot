package com.si.demo.endpoint;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.si.demo.model.Customer;
import com.si.demo.services.CustomerService;

@Component
public class IntermediateEnpoint {
	private Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Resource
	CustomerService custService;

	public Message<?> get(Message<?> msg) {
		log.info("IntermediateEnpoint GET method");
		log.info("msg >>>" + msg);
		log.info("correlation_id >>>" + msg.getHeaders().get("correlation_id"));
		log.info("uuid >>>" + msg.getHeaders().getId());
		return msg;
	}
	
	public void post(Message<Customer> msg){
		log.info("POST method");
		custService.insert(msg.getPayload());
	}
	
	public void put(Message<Customer> msg){
		log.info("PUT method");
		custService.change(msg.getPayload());
	}
	
	public void delete(Message<String>msg){
		log.info("DELETE method");
		int id = Integer.valueOf(msg.getPayload());
		custService.delete(id);
	}
}
