package com.gs.aurora.URL.resource;

import java.sql.Time;
import java.util.Date;

public class URLdata {
	
	String url;
	String date;
	String time;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public URLdata() {
		// TODO Auto-generated constructor stub
	}
	
	public URLdata(String url, String date, String time)
	{
		this.url=url;
		this.date=date;
		this.time=time;
	}

}
