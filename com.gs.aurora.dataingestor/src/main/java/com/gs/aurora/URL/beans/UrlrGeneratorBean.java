package com.gs.aurora.URL.beans;

import javax.ws.rs.QueryParam;

public class UrlrGeneratorBean {
	
	private @QueryParam("username") String userName;
	private @QueryParam("password") String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
