package com.sg.aurora.apigateway.rest.model;

public class URLFormData {
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	String stationName;
	String date;
	String time;
	int userId;
	int requestId;
	
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
	
	public URLFormData(String stationName, String date, String time, int userId, int requestId)
	{
		this.stationName=stationName;
		this.date=date;
		this.time=time;
		this.userId = userId;
		this.requestId = requestId;
	}

}