package com.si.demo.endpoint;

import org.springframework.integration.aggregator.CorrelationStrategy;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public class CustomCorrelationStrategy implements CorrelationStrategy{

	@Override
	public Object getCorrelationKey(Message<?> message) {
		
		System.out.println(">>>>" + message.getHeaders().getId());
   		
		return message.getHeaders().getId();
	}

}
