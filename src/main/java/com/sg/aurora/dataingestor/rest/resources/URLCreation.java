package com.sg.aurora.dataingestor.rest.resources;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.sg.aurora.dataingestor.rest.model.URLFormData;
import com.sg.aurora.dataingestor.rest.model.URLData;

@Path("/urlcreation")
public class URLCreation {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		System.out.println("in GET function");
		return "Got it on urlgen!";
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/generate")
	public URLData getURL(URLFormData urlData) throws ParseException, SQLException {
		URLData urldata=new URLData("dataingestor", "dataingestor", "dataingestor");
		return urldata;

	}
	
	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public URLData getURL(@Context UriInfo uriInfo, @FormParam("datetimepickername") String dateTime,
			@FormParam("nexrad_station") String station) throws ParseException, SQLException {
		
		Date date = new Date();
		String[] dateTimesplit=dateTime.split(" ");
		String dateVar=dateTimesplit[0];
		String time=dateTimesplit[1];
		
		System.out.println(dateTime);
		
		URLData url=new URLData();
		String path = uriInfo.getBaseUriBuilder().path(dateTime).path(station).build().toString();
		URLData urldata=new URLData(path,dateVar,time);
		return urldata;

	}*/

}