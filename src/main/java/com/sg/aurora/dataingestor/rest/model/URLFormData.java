package com.sg.aurora.dataingestor.rest.model;

public class URLFormData {
	
	String stationName;
	String date;
	String time;
	
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