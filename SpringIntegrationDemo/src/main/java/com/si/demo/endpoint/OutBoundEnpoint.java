package com.si.demo.endpoint;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.si.demo.model.Customer;
import com.si.demo.services.CustomerService;



@Component
public class OutBoundEnpoint {
	private Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Resource
	CustomerService custService;

	public Message<?> get(Message<?> msg) {
		log.info("Inbound method");
		
//		Random rand = new Random();
//		//msg.getHeaders().put("correlation_id", rand.nextInt() + "");	
//		log.info("msg >>>" + msg.getPayload().toString());
//		log.info("correlation_id >>>" + msg.getHeaders().get("correlation_id"));
//		log.info("uuid >>>" + msg.getHeaders().getId());
//		List<Customer> custLst = custService.getAll();
//		
//		return MessageBuilder.withPayload(custLst).copyHeadersIfAbsent(msg.getHeaders())
//				.setHeader("http_statusCode", HttpStatus.OK).build();

		log.info("IntermediateEnpoint GET method");
		log.info("msg >>>" + msg);
		List<Object> responses = (List<Object>) msg.getPayload();
		
		JSONObject merged = new JSONObject();		

		String[] keys = new String[] {"account","user"};
		
		int i = 0;
		for(Object ex: responses)
		{
			System.out.println(ex.getClass());
			System.out.println(ex);		
			merged.put(keys[i], new org.json.JSONObject((String)ex));
			i++;
		}
		
		log.info("merged json >>>" + merged.toString());
		
	return MessageBuilder.withPayload(merged.toString()).copyHeadersIfAbsent(msg.getHeaders())
		.setHeader("http_statusCode", HttpStatus.OK).build();
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
