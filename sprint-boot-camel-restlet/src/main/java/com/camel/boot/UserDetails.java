package com.camel.boot;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.camel.CamelContext;
import org.apache.camel.model.ModelHelper;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.rest.RestDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetails {

	@Autowired
	CamelContext camelContext;
	@RequestMapping("/user")
	public String getUserDetails()
	{
		System.out.println("get user details invoked>>");
		dumpRoutes();
		return "emc user";
		
	}
	
	
	private void dumpRoutes()
	{
		 System.out.println("camelContext >>" + camelContext);
		
		  RouteDefinition def = camelContext.getRouteDefinition("serviceFacade"); 
		  List<RouteDefinition> defs = camelContext.getRouteDefinitions();
		  
		  
		  for(RouteDefinition r : defs)
		  {
			  System.out.println("printing details for route >>" + r.getId());
			  
			  try {
				System.out.println(ModelHelper.dumpModelAsXml(camelContext, r));
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  System.out.println("-------------------------------------------");
		  }
		  
		  
		  List<RestDefinition> rdefs =  camelContext.getRestDefinitions();
		  for(RestDefinition r : rdefs)
		  {
			  System.out.println("printing details for REST >>" + r.getId());
			  
			  try {
				System.out.println(ModelHelper.dumpModelAsXml(camelContext, r));
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  System.out.println("-------------------------------------------");
		  }
		  
		  
//		  System.out.println("def >>>>" + def + "defs >>" + defs);
//		  
//		try {
//			String restRoute = ModelHelper.dumpModelAsXml(camelContext, camelContext.getRouteDefinition("rest_route"));
//			String serviceFacade = ModelHelper.dumpModelAsXml(camelContext, def);
//			String rcall3 = ModelHelper.dumpModelAsXml(camelContext, camelContext.getRouteDefinition("rcall3"));
//			String rcall4 = ModelHelper.dumpModelAsXml(camelContext, camelContext.getRouteDefinition("rcall4"));
//			
//			System.out.println(restRoute);
//			System.out.println("-------------");
//			System.out.println(serviceFacade);
//			System.out.println("-------------");
//			System.out.println(rcall3);
//			System.out.println("-------------");
//			System.out.println(rcall4);
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
}
