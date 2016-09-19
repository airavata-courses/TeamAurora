package com.gs.aurora.postgresql;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class PostgresqlDB {

	
	
	public void loggingToDB(String user_id, String station_code,Date date, String time ) throws SQLException
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
				 
				 
				 insertSql="INSERT INTO dataingestor_logging"
						 + "(user_id, station_code, date, time_by_user) VALUES"
						 + "(?,?,?,?)";
						 
			    
				 PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
				 preparedStatement.setInt(1, Integer.parseInt(user_id));
				 preparedStatement.setString(2, station_code);
				 preparedStatement.setDate(3, date);
				 preparedStatement.setString(4, time);
				 //preparedStatement.setTimestamp(5, getCurrentTimeStamp());
				 // execute insert SQL stetement
				 preparedStatement .executeUpdate();
				 System.out.println("Record is inserted into DBUSER table!");
			    
			    /*
			    sql = "SELECT * FROM login_master";
			    ResultSet rs = stmt.executeQuery(sql);

			    //STEP 5: Extract data from result set
			    while(rs.next()){
			       //Retrieve by column name
			       
			       String user_id = rs.getString("user_id");
			       String user_name = rs.getString("user_name");
			       String password = rs.getString("password");
			       String last_login = rs.getString("last_login");
			       
			       System.out.print(", user Id: " + user_id);
			       System.out.println(", user Name: " + user_name);
			       System.out.println(", Password: " + password);
			       System.out.println(", Last login: " + last_login);
			       System.out.println("------------------");
			       
			    }*/
			       /*

			    
			    sql = "SELECT * FROM usermaster";
			       //Display values
			    ResultSet rs = stmt.executeQuery(sql);
			  //STEP 5: Extract data from result set
			    while(rs.next()){
			    	String user_id = rs.getString("userid");
				       String user_name = rs.getString("username");
				       String password = rs.getString("password");
				       String last_login = rs.getString("last_login");
			       System.out.print(", user Id: " + user_id);
			       System.out.println(", user Name: " + user_name);
			       System.out.println(", Password: " + password);
			       System.out.println(", Last login: " + last_login);
			       System.out.println("------------------");
			    }
			    
			    */
			       
			    
			    
			    //STEP 6: Clean-up environment
			    //rs.close();
			    stmt.close();
			    connection.close();
				
			} else {
				System.out.println("Failed to make connection!");
			}
		}
}

