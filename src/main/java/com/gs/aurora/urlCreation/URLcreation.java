package com.gs.aurora.urlCreation;


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

import com.gs.aurora.URL.resource.URLdata;
import com.gs.aurora.postgresql.PostgresqlDB;
import com.gs.*;

@Path("/urlgen")
public class URLcreation {
	
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	//@Produces(MediaType.APPLICATION_XML)
	public String getIt() {
		System.out.println("in GET function");
		return "Got it on urlgen!";
	}
	
	
	
	
	// public String getURL(@Context UriInfo uriInfo, @BeanParam
	// UrlrGeneratorBean urlBeam)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public URLdata getURL(@Context UriInfo uriInfo, @FormParam("datetimepickername") String dateTime,
			@FormParam("nexrad_station") String station) throws ParseException, SQLException {
		
		
		
		PostgresqlDB postDB = new PostgresqlDB();
		Date date = new Date();

		String[] dateTimesplit=dateTime.split(" ");
		String dateVar=dateTimesplit[0];
		String time=dateTimesplit[1];
		
		//postDB.connectionToDB();
		System.out.println(dateTime);
		
		URLdata url=new URLdata();
		
		String path = uriInfo.getBaseUriBuilder().path(dateTime).path(station).build().toString();
		URLdata urldata=new URLdata(path,dateVar,time);
		return urldata;

		
		/*
		DBconnector db = new DBconnector();
		Date date = new Date();

		db.insertURL(username, password, date);

		String path = uriInfo.getBaseUriBuilder().path(username).path(password).build().toString();
		return path;
		*/
		/*
		 * String absolutePath=uriInfo.getAbsolutePath().toString(); String
		 * path=uriInfo.getBaseUriBuilder() .path(urlBeam.getUserName())
		 * .path(urlBeam.getPassword()) .build().toString();
		 * 
		 * return path;
		 */

	}

}
