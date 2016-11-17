package com.sg.aurora.apigateway.rest.service;

import java.util.ArrayList;

import com.sg.aurora.apigateway.rest.dao.RequestDAO;
import com.sg.aurora.apigateway.rest.model.GetStatus;

public class RequestService {
	
	public int generateUserRequest(int userId) {
		RequestDAO requestDAO = new RequestDAO();
		return requestDAO.generateUserRequest(userId);
	}
	
	public ArrayList<GetStatus> getStatus(int userId) {
		RequestDAO requestDAO = new RequestDAO();
		return requestDAO.getStatus(userId);
	}
	
	public void updateServiceStatus(int requestId, String serviceName){
		RequestDAO requestDAO = new RequestDAO();
		requestDAO.updateServiceStatus(requestId,serviceName);
	}
	
	public void updateServiceStatus(int requestId, String serviceName,String status){
		RequestDAO requestDAO = new RequestDAO();
		requestDAO.updateServiceStatus(requestId,serviceName,status);
	}
}