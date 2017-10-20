package com.emc.demo.camel;

import java.util.HashMap;
import java.util.Map;

import org.restlet.ext.spring.SpringServerServlet;
//import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration
@ImportResource({ "classpath*:camel-context.xml" })
public class CamelDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelDemoApplication.class, args);
	}
	
//	  @Bean
//	    public ServletRegistrationBean servletRegistrationBean() {
//		    SpringServerServlet serverServlet = new SpringServerServlet();
//	        ServletRegistrationBean registration = new ServletRegistrationBean(serverServlet, "/service/*");
//	        registration.setName("CamelServlet");
//	        return registration;
//	    }
	
	
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//    	
//    	SpringServerServlet serverServlet = new SpringServerServlet();
//    	ServletRegistrationBean regBean = new ServletRegistrationBean(serverServlet, "");
//    	
//    	
//    	//ServletRegistrationBean regBean = new ServletRegistrationBean( serverServlet, "/rest/*");
//    	
//    	
//    	Map<String,String> params = new HashMap<String,String>();
//    	
//    	params.put("org.restlet.component", "restletComponent");
//    	
//    	regBean.setInitParameters(params);
//    	
//    	return regBean;
//    }

}



@RestController
class APIs {

	@RequestMapping(method = RequestMethod.GET, value = "/createReferral/")
	public @ResponseBody MultiValueMap<String, String> acceptReferral() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.set("status", "done");	
		return params;
	}

}
