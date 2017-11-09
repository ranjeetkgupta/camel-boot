package com.camel.processors;

import java.util.Arrays;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultMessage;

public class SimpleProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
	Object msg1 = exchange.getIn().getBody();
	Object msg2 = exchange.getOut().getBody();
	
	System.out.println("msg >>>>"+ msg1);
	System.out.println("msg >>>>"+ msg2);
		
	}

}
