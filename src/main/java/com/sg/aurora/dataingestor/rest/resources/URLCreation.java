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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sg.aurora.dataingestor.rest.model.URLFormData;

import com.sg.aurora.dataingestor.rest.model.URLData;

@Path("/urlcreation")
public class URLCreation {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/generate")
	public URLData getURL(URLFormData urlFormData) throws ParseException, SQLException {
		URLData urlData=new URLData();
		
		URLFormData urlFormDataVar=new URLFormData();
		
		
		
		PostgreSQLDB postDB = new PostgreSQLDB();
		int user_id=urlFormData.getUserId();
		int request_id=urlFormData.getRequestId();
		String station_name=urlFormData.getStationName();
		//Date date = new Date();
		String service_name="dataIngestor";
		
		String formDate=urlFormData.getDate();
		String formTime=urlFormData.getTime();
		
		System.out.println(formDate);
		System.out.println(formTime);
		
		String dateArr[]=formDate.split("/");
		String timeArr[]=formTime.split(":");

		String year=dateArr[0];
		String month=dateArr[1];
		String date=dateArr[2];
		
		String hours=timeArr[0];
		String minutes=timeArr[1];
		String seconds=timeArr[2];
		String stationName=urlFormData.getStationName();
		System.out.println(year+" "+month+" "+date);
		System.out.println(hours+" "+minutes+" "+seconds);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(urlFormData);
			System.out.println(jsonInString);
			String resultURL="https://aws.amazon.com/noaa-big-data/nexrad/"+urlFormData.getDate()
			+"/"+stationName+"/"+stationName+year+month+date+"_"+hours+minutes+seconds+"_V06";
			
			postDB.loggingToDB(user_id,request_id,service_name,jsonInString,resultURL);
			urlData.setUrl("https://noaa-nexrad-level2.s3.amazonaws.com/2016/09/17/KIND/KIND20160917_000007_V06");
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Code added to add more work for load test.
		int[] a=new int[214748364];
		long sum=0;
		for(int i=0;i<2147483647;i++)
		{
			a[i]=i;
			sum+=i;
			sum-=i;

		}

		for(int i=0;i<2147483647;i++)
		{
			a[i]=10;
		}
		
		return urlData;
	}

}
