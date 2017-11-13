/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.camel.boot;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camel.processors.MyAggregator;
import com.camel.processors.MyProcessor;

/**
 * A Camel route that calls the REST service using a timer
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class RestRoute extends RouteBuilder {

	
	@Autowired
	CamelContext camelContext;
	
	
/*    @Override
    public void configure() throws Exception {
        // call the embedded rest service from the PetController
        //restConfiguration().host("localhost").port(8089);
    	
    	camelContext.getShutdownStrategy().setTimeout(1);
        restConfiguration().component("restlet");

        from("timer:hello?period={{timer.period}}")
            .setHeader("id", simple("${random(1,3)}"))
            .to("rest:get:pets/{id}")
            .log("${body}");
        
        rest("/hello").get().to("direct:hello");
//      from("direct:hello").transform().simple("Hello World!");
//        from("direct:hello").log("started route 2>>>")
//              .to("http4://localhost:8089/user").to("file:target/userInfo").transform().simple("returned !!");
        
        
        from("direct:hello").log("started route >")
        .to("jetty:http://services.groupkt.com/country/get/all").endRest();
    }*/

	
	
    @Override
    public void configure() throws Exception {
    	
    	camelContext.getShutdownStrategy().setTimeout(1);
    	HttpComponent httpComponent = new HttpComponent();
    	camelContext.addComponent("http", httpComponent);
        restConfiguration().component("restlet");     
         
     XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
     xmlJsonFormat.setRootName("input");
     
     
     // 1. Get Account Details with xml unmarshling.
     from("direct:rcall3").
     setHeader(Exchange.HTTP_METHOD,constant(org.apache.camel.component.http4.HttpMethods.POST)).
      setHeader(Exchange.CONTENT_TYPE,constant("application/xml")).
     to("http4://localhost:8091/account/?bridgeEndpoint=true").convertBodyTo(String.class).marshal(xmlJsonFormat).convertBodyTo(String.class);
     
     
     // 2. Get User Details with xml unmarshling.
     from("direct:rcall4").
     log("msg recieved by rcall4>>  ${body}").
     setHeader(Exchange.HTTP_METHOD,constant(org.apache.camel.component.http4.HttpMethods.POST)).
     setHeader(Exchange.CONTENT_TYPE,constant("application/xml")).   
     to("http4://localhost:8091/user/?bridgeEndpoint=true").convertBodyTo(String.class).marshal(xmlJsonFormat).convertBodyTo(String.class);
     
     
     // Wire Account flow and User flow using parallel processing with a direct endpoint 'serviceFacade'
     from("direct:serviceFacade")
     .multicast(new MyAggregator()).parallelProcessing()
       .to("direct:rcall3").to("direct:rcall4")
     .end().process(new MyProcessor());
     
         
     // Expose A rest service with Json interface to invoke the 'serviceFacade' endpoint
	  rest("/apis").description("Books REST service")
	   .post("/getuserdetails").description("get User account Details")
	       .consumes("application/json")
	       .route().routeId("userdetails")
	       .log("msg recieved >>  ${body}")
	       .unmarshal(xmlJsonFormat)
	       .to("direct:serviceFacade")
	       .endRest();
     
     //from("timer:hello?period={{timer.period}}").enrich("direct:serviceFacade").log("endd !!!! ${body}");
   
     
     
    }
}
