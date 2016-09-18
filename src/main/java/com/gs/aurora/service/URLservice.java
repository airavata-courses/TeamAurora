package com.gs.aurora.service;

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


import com.gs.aurora.postgresql.*;



@Path("urlgenold")
// @Consumes(MediaType.APPLICATION_JSON)
public class URLservice {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	//@Produces(MediaType.APPLICATION_JSON)
	public String getIt() {
		return "Got it on urlgen!";
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// public String getURL(@Context UriInfo uriInfo, @BeanParam
	// UrlrGeneratorBean urlBeam)
	public String getURL(@Context UriInfo uriInfo, @FormParam("username") String username,
			@FormParam("password") String password) throws ParseException, SQLException {

		
		PostgresqlDB postDB = new PostgresqlDB();
		Date date = new Date();

		postDB.connectionToDB();

		String path = uriInfo.getBaseUriBuilder().path(username).path(password).build().toString();
		return path;

		
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
