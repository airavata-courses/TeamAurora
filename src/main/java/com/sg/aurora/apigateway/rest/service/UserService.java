package com.sg.aurora.apigateway.rest.service;

import com.sg.aurora.apigateway.rest.dao.UserDAO;

public class UserService {
	
	public boolean validateUser(String userName, String password) {
		UserDAO userDAO = new UserDAO();
		return userDAO.validateUser(userName, password);
	}
}
