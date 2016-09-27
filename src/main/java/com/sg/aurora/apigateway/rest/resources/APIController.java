package com.sg.aurora.apigateway.rest.resources;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import org.json.JSONObject;

import com.sg.aurora.apigateway.rest.service.RequestService;
import com.sg.aurora.apigateway.rest.service.UserService;
import com.sg.aurora.apigateway.rest.model.ClusterData;
import com.sg.aurora.apigateway.rest.model.URLFormData;
import com.sg.aurora.apigateway.util.*;

@Path("/api")
public class APIController {
  
	MyProperties properties=new MyProperties();
	
    @POST
	@Produces(MediaType.APPLICATION_JSON)
  	@Path("/urldata")
	public void getURLInfo(@FormParam("datepicker") String date, @FormParam("timepicker") String time,  @FormParam("nexrad_station") String station, @Context HttpServletRequest request) throws ParseException, SQLException {
  		HttpSession session = request.getSession();
  		int userId = (Integer)session.getAttribute("USERID");
  		RequestService requestService = new RequestService();
  		int requestId = requestService.generateUserRequest(userId);
		UriBuilder builder = UriBuilder.fromPath(request.getContextPath());
		if(requestId != -1){
			try {
				String dataIngestorHost = properties.readPropertiesFile("dataIngestor.host");
			
			  	String dataIngestorPort = properties.readPropertiesFile("dataIngestor.port");
				URLFormData urlFormData = new URLFormData(station, date, time, userId, requestId);
				String dataIngestorServiceURL = dataIngestorHost+":"+dataIngestorPort+"/dataingestor/urlcreation/generate";
				String responseFromDataIngestor = executeAndGetDataIngestorResponse(dataIngestorServiceURL, urlFormData);
				
				JSONObject responseFromDataIngestorJSON = new JSONObject(responseFromDataIngestor);
				responseFromDataIngestorJSON.put("userId", userId);
				responseFromDataIngestorJSON.put("requestId", requestId);
				String stormDetectorHost = properties.readPropertiesFile("stormDetector.host");
			  	String stormDetectorPort = properties.readPropertiesFile("stormDetector.port");
				String stormDetectorServiceURL = stormDetectorHost+":"+stormDetectorPort+"/StormDetector";
				String responseFromStormDetector = executeAndGetStormDetectorResponse(stormDetectorServiceURL, responseFromDataIngestorJSON);
				
				JSONObject responseFromStormDetectorJSON = new JSONObject(responseFromStormDetector);
				responseFromStormDetectorJSON.put("userId", userId);
				responseFromStormDetectorJSON.put("requestId", requestId);
				String stormClusteringHost = properties.readPropertiesFile("stormClustering.host");
			  	String stormClusteringPort = properties.readPropertiesFile("stormClustering.port");
				String stormClusterringServiceURL = stormClusteringHost+":"+stormClusteringPort+"/StormClustering";
				String responseFromStormClusterring = executeAndGetStormClusterringResponse(stormClusterringServiceURL, responseFromStormDetectorJSON);
				
				JSONObject responseFromStormClusteringJSON = new JSONObject(responseFromStormClusterring);
				ClusterData clusterData = new ClusterData(((JSONObject)responseFromStormClusteringJSON.get("results")).get("cluster").toString(), userId, requestId);
				String forecastTriggerHost = properties.readPropertiesFile("forecastTrigger.host");
			  	String forecastTriggerPort = properties.readPropertiesFile("forecastTrigger.port");
				String forecastTriggerServiceURL = forecastTriggerHost+":"+forecastTriggerPort+"/forecasttrigger/detected/storm";
				ClusterData responseFromForecastTrigger = executeAndGetForecastTriggerResponse(forecastTriggerServiceURL, clusterData);
				System.out.println("Does STORM Exists :: " + responseFromForecastTrigger.isStormExists());
				if(responseFromForecastTrigger.isStormExists()){
					builder.path("/jsp/stormexist.jsp");
				}
				else
				{
					builder.path("/jsp/stormnotexist.jsp");
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		return Response.seeOther(builder.build()).build();
    }
  
  	public String executeAndGetDataIngestorResponse( String incomingURL, URLFormData urlFormData) {
	  	System.out.println("Call to executeAndGetDataIngestorResponse :: " + incomingURL);
		Client client = ClientBuilder.newClient();
		Response response = client.target(incomingURL).request().post(Entity.json(urlFormData));
		return response.readEntity(String.class);		
  	}
  	
  	public String executeAndGetStormDetectorResponse(String incomingURL, JSONObject responseFromDataIngestorJSON) {
  	  System.out.println("Call to executeAndGetStormDetectorResponse :: " + incomingURL);
	  Client client = ClientBuilder.newClient();
	  Response response = client.target(incomingURL).request().post(Entity.json(responseFromDataIngestorJSON.toString()));
	  System.out.println("Status of call to Storm Detector :: " + response.getStatus());
	  return response.readEntity(String.class);
    }	
	
  	public String executeAndGetStormClusterringResponse( String incomingURL, JSONObject responseFromStormDetectorJSON) {
	  System.out.println("Call to executeAndGetStormClusterringResponse :: " + incomingURL);
	  Client client = ClientBuilder.newClient();
  	  Response response = client.target(incomingURL).request().post(Entity.json(responseFromStormDetectorJSON.toString()));
  	  System.out.println("Status of call to Storm Detector :: " + response.getStatus());
  	  return response.readEntity(String.class);
    }
  	
  	public ClusterData executeAndGetForecastTriggerResponse(String incomingURL, ClusterData clusterData) {
	  	System.out.println("Call to executeAndGetForecastTriggerResponse :: " + incomingURL);
		Client client = ClientBuilder.newClient();
		Response response = client.target(incomingURL).request().post(Entity.json(clusterData));
		return response.readEntity(ClusterData.class);
  	}
  	
  @POST
  @Path("/login")
  public Response validateUser(@FormParam("username") String userName, @FormParam("password") String password, @Context HttpServletRequest request) throws URISyntaxException {
	UserService userService = new UserService();
	URI targetURIForRedirection = null;
	int userId = userService.validateUser(userName, password);
	if(userId != -1){
		HttpSession session= request.getSession(true);
		session.setAttribute("USERID", userId);
		session.setAttribute("USERNAME", userName);
		targetURIForRedirection = new URI(request.getContextPath()+"/jsp/client.jsp");
	}
	else{
		targetURIForRedirection = new URI(request.getContextPath()+"/jsp/login.jsp");
	}
	return Response.seeOther(targetURIForRedirection).build();
  }
  
}
