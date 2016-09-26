package com.sg.aurora.apigateway.rest.service;

import com.sg.aurora.apigateway.rest.dao.RequestDAO;

public class RequestService {
	
	public int generateUserRequest(int userId) {
		RequestDAO requestDAO = new RequestDAO();
		return requestDAO.generateUserRequest(userId);
	}
}