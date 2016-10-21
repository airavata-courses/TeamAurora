package com.sg.aurora.apigateway.rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
	
	public int addGoogleUser(String username){
		int generatedUserId = -1;
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://teamaurora-db.cfofssvi9hbo.us-west-2.rds.amazonaws.com:5432/sg_teamaurora_db", "dbadmin", "teamauroradbadmin");
			
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String sql = "INSERT INTO login_master (user_name, oauth) VALUES (?,?)";
				ResultSet rs = null;
				PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, "true");
				preparedStatement .executeUpdate();
				rs = preparedStatement.getGeneratedKeys();
			    if(rs != null && rs.next()){
			    	generatedUserId = rs.getInt("user_id");
			    	System.out.println("Generated User Id: " + rs.getInt("user_id"));
			    }
				rs.close();
				stmt.close();
				connection.close();
				
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return generatedUserId;
	}
	
	
	public int validateUser(String userName, String password){
		boolean isLoginSuccessful = false;
		int userId = -1;
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
				   userId = rs.getInt("user_id");
				   isLoginSuccessful = true;
				}
				rs.close();
				stmt.close();
				connection.close();
				
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return userId;
	}
	
	public int validateUser(String userName){
		boolean isLoginSuccessful = false;
		int userId = -1;
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://teamaurora-db.cfofssvi9hbo.us-west-2.rds.amazonaws.com:5432/sg_teamaurora_db", "dbadmin", "teamauroradbadmin");
			
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String sql = "SELECT * FROM login_master where user_name = '" + userName + "'";
				ResultSet rs = stmt.executeQuery(sql);

				while(rs.next()){
				   //Retrieve by column name
				   String user_name = rs.getString("user_name");
				   System.out.println("Login successful for: " + user_name);
				   userId = rs.getInt("user_id");
				   isLoginSuccessful = true;
				}
				rs.close();
				stmt.close();
				connection.close();
				
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return userId;
	}

	public int generateUserRequest(int userId){
		int requestId = -1;
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://teamaurora-db.cfofssvi9hbo.us-west-2.rds.amazonaws.com:5432/sg_teamaurora_db", "dbadmin", "teamauroradbadmin");
			
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String sql = "INSERT INTO request_master (user_id) VALUES (?)";
				ResultSet rs = null;
				PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, userId);
				preparedStatement .executeUpdate();
				rs = preparedStatement.getGeneratedKeys();
			    if(rs != null && rs.next()){
			    	requestId = rs.getInt(1);
			    	System.out.println("Generated Request Id: " + rs.getInt(1));
			    }
				rs.close();
				stmt.close();
				connection.close();
				
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return requestId;
	}

}
