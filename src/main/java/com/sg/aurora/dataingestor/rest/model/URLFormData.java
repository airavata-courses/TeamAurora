package com.sg.aurora.dataingestor.rest.model;

public class URLFormData {
	
	String stationName;
	String date;
	String time;
	int user_id;
	int request_id;
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRequest_id() {
		return request_id;
	}

	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
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

	public URLFormData() {
		// TODO Auto-generated constructor stub
	}
	
	public URLFormData(String stationName, String date, String time)
	{
		this.stationName=stationName;
		this.date=date;
		this.time=time;
	}

}