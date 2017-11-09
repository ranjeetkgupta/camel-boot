package com.camel.processors;

import java.util.Arrays;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultMessage;

public class MyProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
	Object msg = exchange.getIn().getBody();
	List<Object> exchanges = (List<Object>) msg;
	for(Object ex: exchanges)
	{
		System.out.println(ex.getClass());
		System.out.println(ex);
	
		
	}
	System.out.println("msg >>>>"+ msg);
		
	}

}
