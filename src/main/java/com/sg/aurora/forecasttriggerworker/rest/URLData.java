package com.sg.aurora.forecasttriggerworker.rest;

import java.sql.Time;
import java.util.Date;

public class URLData {
	
	String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

	public URLData() {
		// TODO Auto-generated constructor stub
	}
	
	public URLData(String url, String date, String time)
	{
		this.url=url;
	
	}

}