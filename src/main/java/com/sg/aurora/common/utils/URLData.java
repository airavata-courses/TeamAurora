package com.sg.aurora.common.utils;

import java.io.Serializable;

import com.sg.aurora.common.utils.Service;

public class URLData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4627676750311616394L;

	String url;
	
	Service fromService;
	Service toService;
	int userId;
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

	int requestId;
	
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