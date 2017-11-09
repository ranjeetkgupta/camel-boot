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
import org.apache.camel.processor.aggregate.GroupedExchangeAggregationStrategy;
import org.apache.camel.processor.aggregate.GroupedMessageAggregationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camel.processors.MyAggregator;
import com.camel.processors.MyProcessor;
import com.camel.processors.SimpleProcessor;

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
        // call the embedded rest service from the PetController
        //restConfiguration().host("localhost").port(8089);
    	
    	camelContext.getShutdownStrategy().setTimeout(1);
    	HttpComponent httpComponent = new HttpComponent();
    	camelContext.addComponent("http", httpComponent);
        restConfiguration().component("restlet");
        
//        restConfiguration()
//        .contextPath("/camel-rest-jpa").apiContextPath("/api-doc")
//            .apiProperty("api.title", "Camel REST API")
//            .apiProperty("api.version", "1.0")
//            .apiProperty("cors", "true")
//            .apiContextRouteId("doc-api")
//        .bindingMode(RestBindingMode.json);

     rest("/books").description("Books REST service")
        .get("/getbooks").description("The list of all the books")
            .route().routeId("books1")
            .transform().simple("Hello World!")          
            .endRest();
        
        
//    rest("/books").description("Books REST service")
//        .get("/getall").description("The list of all the books")
//            .route().routeId("books2")
//            .to("http4://localhost:8089/books/getbooks")
//            .to("file:target/userInfo")
//            //.bean(Database.class, "findBooks")
//            .endRest();
    
//    rest("/books").description("Books REST service")
//    .get("/getall").description("The list of all the books")
//        .route().routeId("books2")
//        .to("http4://localhost:8089/books/getbooks")
//        .to("file:target/userInfo")
//    
//        .endRest();
    
//    from("timer:hello?period={{timer.period}}")
//    .setHeader("id", simple("${random(1,3)}"))
//    .to("jetty:http://services.groupkt.com/state/get/IND/UP")
//    .log("${body}");
     
     
    //property(Exchange.GROUPED_EXCHANGE)
    
//		from("timer:hello?period={{timer.period}}")
//				.multicast(new GroupedExchangeAggregationStrategy()).parallelProcessing()
//				.enrich("jetty:http://localhost:8080/address")
//				.enrich("jetty:http://localhost:8080/user").log("ended !!!").setBody(exchangeProperty(Exchange.GROUPED_EXCHANGE)).log("${body}");
		
//		from("timer:hello?period={{timer.period}}")
//		.multicast(new GroupedMessageAggregationStrategy()).parallelProcessing()
//		.to("jetty:http://localhost:8080/address")
//		.to("jetty:http://localhost:8080/user").log("ended !!!").log("${header}");
     
     
     
     from("direct:rcall1").to("http://localhost:8080/user").convertBodyTo(String.class);
     from("direct:rcall2").to("http://localhost:8080/address").convertBodyTo(String.class);
     
     
//     from("direct:serviceFacade")
//     .multicast(new MyAggregator()).parallelProcessing()
//       .enrich("http://localhost:8080/user").enrich("http://localhost:8080/address")
//     .end().convertBodyTo(String.class).process(new MyProcessor());
     
     
     from("direct:serviceFacade")
     .multicast(new MyAggregator()).parallelProcessing()
       .to("direct:rcall1").to("direct:rcall2")
     .end().process(new MyProcessor());
     
//     from("direct:serviceFacade")
//        .to("http://localhost:8080/user").convertBodyTo(String.class).log("${body}")
//       .process(new SimpleProcessor());

     
     
     
     
     from("timer:hello?period={{timer.period}}").enrich("direct:serviceFacade").log("endd !!!! ${body}");
     // setBody(property(Exchange.GROUPED_EXCHANGE))
     
     
    }
}
