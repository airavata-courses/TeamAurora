package com.sg.aurora.apigateway.rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
	public boolean validateUser(String userName, String password){
		boolean isLoginSuccessful = false;
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://teamaurora-db.cfofssvi9hbo.us-west-2.rds.amazonaws.com:5432/sg_teamaurora_db", "dbadmin", "teamauroradbadmin");
			
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String sql = "SELECT * FROM login_master where user_name = '" + userName + "' AND password = '" + password +"'";
				ResultSet rs = stmt.executeQuery(sql);

				while(rs.next()){
				   //Retrieve by column name
				   String user_name = rs.getString("user_name");
				   System.out.println("Login successful for: " + user_name);
				   isLoginSuccessful = true;
				}
				rs.close();
				stmt.close();
				connection.close();
				
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isLoginSuccessful;
	}
}