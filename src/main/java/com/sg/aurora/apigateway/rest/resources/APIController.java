package com.sg.aurora.apigateway.rest.resources;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.ws.rs.core.UriBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.aurora.apigateway.rabbitmq.producer.MessageSender;
import com.sg.aurora.apigateway.rest.service.RequestService;
import com.sg.aurora.apigateway.rest.service.UserService;
import com.sg.aurora.common.utils.Constants;
import com.sg.aurora.common.utils.Service;
import com.sg.aurora.common.utils.URLFormData;
import com.sg.aurora.apigateway.util.*;

@Path("/api")
public class APIController {
  
	MyProperties properties=new MyProperties();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ping")
	public String isServiceRunning(){
		return "{\"isServiceRunning\":true}";
	}
	
    @POST
	@Produces(MediaType.APPLICATION_JSON)
  	@Path("/urldata")
	public Response getURLInfo(@FormParam("datepicker") String date, @FormParam("timepicker") String time,  @FormParam("nexrad_station") String station, @Context HttpServletRequest request) throws ParseException, SQLException {
    	
    	HttpSession session = request.getSession();
  		int userId = (Integer)session.getAttribute("USERID");
  		RequestService requestService = new RequestService();
  		int requestId = requestService.generateUserRequest(userId);
  		requestService.updateServiceStatus(requestId, "API Gateway","In Progress");
		UriBuilder builder = UriBuilder.fromPath(request.getContextPath());
		if(requestId != -1){
			try {
				
				URLFormData urlFormData = new URLFormData(station, date, time, userId, requestId);
				urlFormData.setFromService(Service.APIGATEWAY);
				urlFormData.setToService(Service.DATAINGESTOR);
				
				ObjectMapper mapper = new ObjectMapper();
				String messageInString = mapper.writeValueAsString(urlFormData);
				System.out.println(messageInString);
				MessageSender.sendMessageToMicroServices(messageInString, Service.DATAINGESTOR, Constants.APIGATEWAY_DATAINGESTOR_QUEUE);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return Response.seeOther(builder.build()).build();
    }
  	
  @POST
  @Path("/login")
  public Response validateUser(@FormParam("google") String google, @FormParam("username") String userName, @FormParam("password") String password, @Context HttpServletRequest request, @Context HttpServletResponse response) throws URISyntaxException, IOException, ServletException {
	UserService userService = new UserService();
	URI targetURIForRedirection = null;
	
	int userId =-1;
	if(google==null)
	{
		
		userId= userService.validateUser(userName, password);
		if(userId != -1 && password!=null){
			HttpSession session= request.getSession(true);
			session.setAttribute("USERID", userId);
			session.setAttribute("USERNAME", userName);
			targetURIForRedirection = new URI(request.getContextPath()+"/jsp/jobs.jsp");
		}
		else{
			targetURIForRedirection = new URI(request.getContextPath()+"/jsp/login.jsp");
		}
	}
	else
	{
		userId=userService.validateUser(userName);
		if(userId==-1)
			userId = userService.addGoogleUser(userName);
		HttpSession session= request.getSession(true);
		session.setAttribute("USERID", userId);
		session.setAttribute("USERNAME", userName);
		targetURIForRedirection = new URI(request.getContextPath()+"/jsp/jobs.jsp");
	}
	return Response.seeOther(targetURIForRedirection).build();
  }
  
}
