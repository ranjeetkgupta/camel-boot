package com.si.demo.transformer;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

@Component
public class CustomTransformer {

	@Transformer
    public String xmlToJson(String xmlPayload)  {
		System.out.println("Xml payload recieved >>" + xmlPayload);		
		JSONObject xmlJSONObj = XML.toJSONObject(xmlPayload);
        String json = xmlJSONObj.toString();
        System.out.println(json);		
		return json;
	
	}
	
	
	@Transformer
    public String jsonToXml(String jsonPayload)  {
		System.out.println("Json payload recieved >>" + jsonPayload);		
		JSONObject json = new JSONObject(jsonPayload);	
		String xml = XML.toString(json,"input");
		System.out.println("xml >>>" + xml);
		return xml;
	
	}
	
	
}
