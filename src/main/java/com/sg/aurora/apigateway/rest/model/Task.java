package com.sg.aurora.apigateway.rest.model;

public class Task {
	String name;
	String status;
	
	public Task(String name, String status){
		this.name = name;
		this.status = status;
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
