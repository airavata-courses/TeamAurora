package com.sg.aurora.common.utils;

import java.io.Serializable;

public class ClusterData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6305678385621900288L;
	String clusters;
	boolean stormExists;
	
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

	
	

	public boolean isStormExists() {
		return stormExists;
	}

	public void setStormExists(boolean stormExists) {
		this.stormExists = stormExists;
	}

	public String getClusters() {
		return clusters;
	}

	public void setClusters(String clusters) {
		this.clusters = clusters;
	}

	int userId;
	int requestId;
	
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
