package com.sg.aurora.ForecastTrigger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.glass.ui.Application;

@Path("/detected")
public class ForecastDetect {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void get() {
    	System.out.println("GET METHOD");
        //return "Got it!";
    }
    
    @POST
    @Path("/storm")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON) 
	public Response results(String storm) throws SQLException, IOException {
    	
    	URLFormData urlFormData=new URLFormData();
    	PostgreSQLDB postDB = new PostgreSQLDB();
		
    	int user_id = urlFormData.getUser_id();
		int request_id = urlFormData.getRequest_id();
		String service_name="runWeatherForecast";
		
		System.out.println("Deciding on Storm");
		Random rand = new Random();
		int val = rand.nextInt();
		
		if(val > 1){
			System.out.println("{results:['Storm exists']}");
			postDB.loggingToDB(user_id,request_id,service_name,storm,"Storm Exist");
			return Response.status(200).entity("{results:['Yes']}").build();
		}
		
		else{
			System.out.println("{results:['NO Storm']}");
			postDB.loggingToDB(user_id,request_id,service_name,storm,"No Storm");
			return Response.status(200).entity("{results:['NO']}").build();
		}
		
    }
}
