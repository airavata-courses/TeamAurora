package com.sg.aurora.forecasttriggerworker.rest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sg.aurora.forecasttriggerworker.rest.MyProperties;



public class PostgreSQLDB {


	MyProperties properties=new MyProperties();
	
	
	public int updateServiceStatus(int requestId, String serviceName){
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			String dbHost=properties.readPropertiesFile("db.host");
			String dbPort=properties.readPropertiesFile("db.port");
			String dbName=properties.readPropertiesFile("db.name");
			String dbUserName=properties.readPropertiesFile("db.username");
			String dbPassword=properties.readPropertiesFile("db.password");
			connection = DriverManager.getConnection(
					"jdbc:postgresql://"+dbHost+":"+dbPort+"/"+dbName, dbUserName,
					dbPassword);
			
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestId;
	}

	
	
	public void loggingToDB(int user_id, int request_id,  
			String service_name, String input, String output ) throws SQLException, IOException
	{
		Statement stmt = null;
		try {
				Class.forName("org.postgresql.Driver");

			} catch (ClassNotFoundException e) {			
				e.printStackTrace();
				return;
			}
		Connection connection = null;
			try {

				/*
				 * connection = DriverManager.getConnection(
						"jdbc:postgresql://127.0.0.1:5432/sg_teamaurora_db", "postgres",
						"root");
				*/
				String dbHost=properties.readPropertiesFile("db.host");
				String dbPort=properties.readPropertiesFile("db.port");
				String dbName=properties.readPropertiesFile("db.name");
				String dbUserName=properties.readPropertiesFile("db.username");
				String dbPassword=properties.readPropertiesFile("db.password");
				connection = DriverManager.getConnection(
						"jdbc:postgresql://"+dbHost+":"+dbPort+"/"+dbName, dbUserName,
						dbPassword);
			} catch (SQLException e) {

				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return;

			}

			if (connection != null) {
				stmt = connection.createStatement();
			    String insertSql;
			     insertSql="INSERT INTO service_requests_logger"
						 + "(user_id, request_id, service_name, input, output) VALUES"
						 + "(?,?,?,?,?)";
				 ResultSet rs = null;
				 PreparedStatement preparedStatement = connection.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS);
				 preparedStatement.setInt(1, user_id);
				 preparedStatement.setInt(2, request_id);
				 preparedStatement.setString(3, service_name);
				 preparedStatement.setString(4, input);
				 preparedStatement.setString(5, output);
				 preparedStatement .executeUpdate();
				 System.out.println("Record is inserted into service_requests_logger table!");
				 rs = preparedStatement.getGeneratedKeys();
		            if(rs != null && rs.next()){
		                System.out.println("Generated Id: "+rs.getInt(1));
		            }
			    stmt.close();
			    connection.close();
				
			} else {
				System.out.println("Failed to make connection!");
			}
		}
	
	
}
