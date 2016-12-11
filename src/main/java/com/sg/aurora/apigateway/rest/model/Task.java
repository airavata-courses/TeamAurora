package com.sg.aurora.apigateway.rest.model;

public class Task {
	String name;
	String status;
	String host;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Task(String name, String status, String host){
		this.name = name;
		this.status = status;
		this.host = host;
	}
	
	public Task(){
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
