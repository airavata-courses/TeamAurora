package com.gs.aurora.postgresql;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class PostgresqlDB {

	
	
	public void connectionToDB() throws SQLException
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
			    String sql;
			    
			    
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
			       
			    }
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
			    rs.close();
			    stmt.close();
			    connection.close();
				
			} else {
				System.out.println("Failed to make connection!");
			}
		}
}

