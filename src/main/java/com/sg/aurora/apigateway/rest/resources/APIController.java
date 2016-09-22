package com.sg.aurora.apigateway.rest.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import org.glassfish.jersey.client.ClientConfig;

import com.sg.aurora.apigateway.rest.service.UserService;
import com.sg.aurora.apigateway.rest.model.URLFormData;



@Path("/api")
public class APIController {
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/detectstorm")
  public String detectstorm() {
	System.out.println("Inside Detect Storm");
	Response response = invokeRestService("http://localhost:9090/dataIngestor/webapi/urlgen");
	String str = response.readEntity(String.class);
	System.out.println(str);
	return "{}";
  }
  
  	@POST
	@Produces(MediaType.APPLICATION_JSON)
  	@Path("/urldata")
	public void getURLInfo(@FormParam("datetimepickername") String dateTime, @FormParam("nexrad_station") String station) throws ParseException, SQLException {
	  URLFormData urlFormData = new URLFormData(station, dateTime, "");
  	  Client client = ClientBuilder.newClient();
	  Response response = client.target("http://localhost:8080/dataingestor/urlcreation/generate").request().post(Entity.json(urlFormData));
	  System.out.println("Status of call to DataIngestor :: " + response.getStatus());
	  String str = response.readEntity(String.class);
	  System.out.println(str);
	  
	  String stormDetectorURL = "http://127.0.0.1:5000/https://noaa-nexrad-level2.s3.amazonaws.com/2016/09/17/KIND/KIND20160917_000007_V06";
	  String responseFromStormDetector = getStormDetectorInfo(stormDetectorURL);
	  System.out.println("responseFromStormDetector :: " + responseFromStormDetector);
	  
	  String stormClusterringURL = "http://127.0.0.1:5050/StormClustering";
	  getStormClusterringInfo(stormClusterringURL, responseFromStormDetector);
    }
  
  	public String getStormDetectorInfo( String incomingURL) {
  	  Client client = ClientBuilder.newClient();
	  Response response = client.target(incomingURL).request().get();
	  System.out.println("Status of call to Storm Detector :: " + response.getStatus());
	  return response.readEntity(String.class);
    }	
	
  	public String getStormClusterringInfo( String incomingURL, String responseFromStormDetector) {
	  Client client = ClientBuilder.newClient();
  	  Response response = client.target(incomingURL).request().post(Entity.json(responseFromStormDetector));
  	  System.out.println("Status of call to Storm Detector :: " + response.getStatus());
  	  return response.readEntity(String.class);
    }	
  	
  @POST
  @Path("/login")
  public Response validateUser(@FormParam("username") String userName, @FormParam("password") String password, @Context HttpServletRequest request) throws URISyntaxException {
	UserService userService = new UserService();
	URI targetURIForRedirection = null;
	if(userService.validateUser(userName, password)){
		HttpSession session= request.getSession(true);
		session.setAttribute("USERNAME", userName);
		//invokeDataIngestor();
		targetURIForRedirection = new URI(request.getContextPath()+"/jsp/client.jsp");
	}
	else{
		targetURIForRedirection = new URI(request.getContextPath()+"/jsp/login.jsp");
	}
	return Response.seeOther(targetURIForRedirection).build();
  }
  
  public void invokeDataIngestor(){
	invokeRestService("http://localhost:8080/dataingestor/");
  }
  
  public void invokeStormDetector()
  {
	  invokeRestService("http://127.0.0.1:5000/https://noaa-nexrad-level2.s3.amazonaws.com/2016/09/17/KIND/KIND20160917_000007_V06");
  }
  
  public Response invokeRestService(String serviceURI){
    Client client = ClientBuilder.newClient();
	Response response = client.target(serviceURI).request().get();
	System.out.println("Status :: " + response.getStatus());
	return response;	
  }
  
}