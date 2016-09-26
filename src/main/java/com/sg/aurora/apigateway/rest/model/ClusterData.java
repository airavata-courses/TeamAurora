package com.sg.aurora.apigateway.rest.model;

public class ClusterData {
	
	String clusters;
	int userId;
	int requestId;
	
	boolean isStormExists;
	
	public boolean isStormExists() {
		return isStormExists;
	}

	public void setStormExists(boolean isStormExists) {
		this.isStormExists = isStormExists;
	}
	
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

	public String getClusters() {
		return clusters;
	}

	public void setClusters(String clusters) {
		this.clusters = clusters;
	}
	
	public ClusterData() {
		// TODO Auto-generated constructor stub
	}
	
	public ClusterData(String clusters, int userId, int requestId)
	{
		this.clusters = clusters;
		this.userId = userId;
		this.requestId = requestId;
	}

}