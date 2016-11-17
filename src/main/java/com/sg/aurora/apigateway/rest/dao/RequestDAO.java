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
	
public int updateServiceStatus(int requestId, String serviceName,String status){
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://teamaurora-db.cfofssvi9hbo.us-west-2.rds.amazonaws.com:5432/sg_teamaurora_db", "dbadmin", "teamauroradbadmin");
			
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String sql = "UPDATE request_master SET service_name=?, status=? WHERE request_id=?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, serviceName);
				preparedStatement.setString(2, status);
				preparedStatement.setInt(3, requestId);
				preparedStatement .executeUpdate();
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
	
	public int updateServiceStatus(int requestId, String serviceName){
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://teamaurora-db.cfofssvi9hbo.us-west-2.rds.amazonaws.com:5432/sg_teamaurora_db", "dbadmin", "teamauroradbadmin");
			
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String sql = "UPDATE request_master SET service_name=? WHERE request_id=?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, serviceName);
				preparedStatement.setInt(2, requestId);
				preparedStatement .executeUpdate();
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
	
	public ArrayList<GetStatus> getStatus(int userId){
		ArrayList<GetStatus> result=new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://teamaurora-db.cfofssvi9hbo.us-west-2.rds.amazonaws.com:5432/sg_teamaurora_db", "dbadmin", "teamauroradbadmin");
			
			if (connection != null) {
				Statement stmt = connection.createStatement();
				String sql = "Select * from request_master where user_id='"+userId+"' ORDER BY request_id DESC";
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