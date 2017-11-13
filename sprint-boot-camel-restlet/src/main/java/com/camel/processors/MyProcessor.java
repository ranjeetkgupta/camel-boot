package com.camel.processors;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;


public class MyProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
	Object msg = exchange.getIn().getBody();
	List<Object> exchanges = (List<Object>) msg;
	JSONObject merged = new JSONObject();
	

	String[] keys = new String[] {"account","user"};
	
	int i = 0;
	for(Object ex: exchanges)
	{
		System.out.println(ex.getClass());
		System.out.println(ex);		
		merged.put(keys[i], new org.json.JSONObject((String)ex));
		i++;
	}
	
	exchange.getIn().setBody(merged.toString());
	System.out.println("merged Json >>>>"+ merged.toString());
	
	
	}

}
