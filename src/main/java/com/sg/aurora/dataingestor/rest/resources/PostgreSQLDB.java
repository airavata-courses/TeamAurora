package com.sg.aurora.dataingestor.rest.resources;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLDB {

	
	public void loggingToDB(int user_id, int request_id,  
			String service_name, String input, String output ) throws SQLException
	{
		Statement stmt = null;

			System.out.println("-------- PostgreSQL "
					+ "JDBC Connection Testing ------------");

			try {

				Class.forName("org.postgresql.Driver");

			} catch (ClassNotFoundException e) {

				System.out.println("Where is your PostgreSQL JDBC Driver? "
						+ "Include in your library path!");
				e.printStackTrace();
				return;

			}

			System.out.println("PostgreSQL JDBC Driver Registered!");

			Connection connection = null;

			try {

				/*
				 * connection = DriverManager.getConnection(
						"jdbc:postgresql://127.0.0.1:5432/sg_teamaurora_db", "postgres",
						"root");
				*/
				connection = DriverManager.getConnection(
						"jdbc:postgresql://teamaurora-db.cfofssvi9hbo.us-west-2.rds.amazonaws.com:5432/sg_teamaurora_db", "dbadmin",
						"teamauroradbadmin");

			} catch (SQLException e) {

				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return;

			}

			if (connection != null) {
				System.out.println("You made it, take control your database now!");
				//STEP 4: Execute a query
			    System.out.println("Creating statement...");
			    stmt = connection.createStatement();
			    String insertSql;
			    
			    
				 //sql="INSERT INTO dataingestor_logging VALUES('"+user_id+"', '"+station_code+"', '"+date+"', '"+time+"');";
				 
				 
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
				 //preparedStatement.setTimestamp(5, getCurrentTimeStamp());
				 // execute insert SQL stetement
				 preparedStatement .executeUpdate();
				 System.out.println("Record is inserted into service_requests_logger table!");
				 rs = preparedStatement.getGeneratedKeys();
		            if(rs != null && rs.next()){
		                System.out.println("Generated Emp Id: "+rs.getInt(1));
		            }
		            
		            //www.java2novice.com/jdbc/auto-generated-keys/#sthash.KObjtEsO.dpuf
				/* if(returnLastInsertId!=null) {
					   ResultSet rs = stmt.getGeneratedKeys();
					    rs.next();
					   auto_id = rs.getInt(1);
					}
				 */
				 /*
				 SELECT `AUTO_INCREMENT`
				 FROM  INFORMATION_SCHEMA.TABLES
				 WHERE TABLE_SCHEMA = 'DatabaseName'
				 AND   TABLE_NAME   = 'TableName';
				 
				 */
			    stmt.close();
			    connection.close();
				
			} else {
				System.out.println("Failed to make connection!");
			}
		}
	
	
}
