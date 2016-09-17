package com.sg.aurora.apigateway.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/apiservice")
public class APIService {
  
  // This method is called if HTML is request
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHtmlHello() {
	  System.out.println("hello");
    return "<html> " + "<title>" + "Hello from API Gateway" + "</title>"
        + "<body><h1>" + "Hello from API Gateway!" + "</h1></body>" + "</html> ";
  }

}