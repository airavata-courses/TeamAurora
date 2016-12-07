package com.sg.aurora.common.utils;

import java.io.Serializable;

public class URLFormData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2423970663134299314L;

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
	Service fromService;
	Service toService;
	
	public Service getFromService() {
		return fromService;
	}

	public void setFromService(Service fromService) {
		this.fromService = fromService;
	}

	public Service getToService() {
		return toService;
	}

	public void setToService(Service toService) {
		this.toService = toService;
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
	
	public URLFormData(String stationName, String date, String time, int userId, int requestId)
	{
		this.stationName=stationName;
		this.date=date;
		this.time=time;
		this.userId = userId;
		this.requestId = requestId;
	}

}