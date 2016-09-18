package com.sg.aurora.ForecastTrigger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	public Response results(boolean storm) {
		System.out.println("Deciding on Storm");
		if(storm){
			return Response.status(200).entity("{results:['YES']}").build();
		}
		else
			return Response.status(200).entity("{results:['NO']}").build();
	}
}
