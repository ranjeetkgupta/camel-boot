package com.si.demo.endpoint;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.si.demo.model.Customer;
import com.si.demo.services.CustomerService;



@Component
public class InboundEnpoint {
	private Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Resource
	CustomerService custService;

	public Message<?> get(Message<?> msg) {
		log.info("Inbound method");
		
		Random rand = new Random();
		log.info("IntermediateEnpoint GET method");
		log.info("msg >>>" + msg);
		log.info("correlation_id >>>" + msg.getHeaders().get("correlation_id"));
		log.info("cid >>>" + msg.getHeaders().get("cid")+"");		
		return MessageBuilder.fromMessage(msg).setHeader("cid", rand.nextInt() + "").build();	
		
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
