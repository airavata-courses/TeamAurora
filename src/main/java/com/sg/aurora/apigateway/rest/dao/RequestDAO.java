package com.sg.aurora.apigateway.rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sg.aurora.apigateway.rest.model.GetStatus;

public class RequestDAO {
	
	public ArrayList<GetStatus> getStatus(int userId){
		ArrayList<GetStatus> result=new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://teamaurora-db.cfofssvi9hbo.us-west-2.rds.amazonaws.com:5432/sg_teamaurora_db", "dbadmin", "teamauroradbadmin");
			
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String sql = "Select * from request_master where user_id='"+userId+"'";
				ResultSet rs = stmt.executeQuery(sql);
			      // iterate through the java resultset
			      while (rs.next())
			      {  
			    	  GetStatus gsObj= new GetStatus();
			    	  gsObj.setRequestId(rs.getInt("request_id"));
			    	  gsObj.setServiceName(rs.getString("service_name"));
			    	  gsObj.setStartTime(rs.getString("timestamp"));
			    	  gsObj.setStatus(rs.getString("status"));
				      result.add(gsObj);
				  }
			      stmt.close();
			    } else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return result;	
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
			    	requestId = rs.getInt("request_id");
			    	System.out.println("Generated Request Id: " + rs.getInt("request_id"));
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