package com.gs.aurora.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBconnector {
	
	// JDBC driver name and database URL
		 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		 static final String DB_URL = "jdbc:mysql://localhost/learning";

		 //  Database credentials
		 final String USER = "root";
		 final String PASS = "root";
		 Connection conn = null;
		 Statement stmt = null;
		 
		 public DBconnector() {
			 /*
			 try{
			    //STEP 2: Register JDBC driver
			    Class.forName("com.mysql.jdbc.Driver");

			    //STEP 3: Open a connection
			    System.out.println("Connecting to database...");
			    conn = DriverManager.getConnection(DB_URL,USER,PASS);

			    //STEP 4: Execute a query
			    System.out.println("Creating statement...");
			    stmt = conn.createStatement();
			 
			//STEP 6: Clean-up environment
			    stmt.close();
			    conn.close();
			 
			 }catch(SQLException se){
			    //Handle errors for JDBC
			    se.printStackTrace();
			 }catch(Exception e){
			    //Handle errors for Class.forName
			    e.printStackTrace();
			 }finally{
			    //finally block used to close resources
			    try{
			       if(stmt!=null)
			          stmt.close();
			    }catch(SQLException se2){
			    }// nothing we can do
			    try{
			       if(conn!=null)
			          conn.close();
			    }catch(SQLException se){
			       se.printStackTrace();
			    }//end finally try
			 }//end try
			 
			 */
		}
		 
		 public  void insertURL(String username, String station, Date date) throws ParseException {
			 
			 java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			 

			 
			 
			 
				 Connection conn = null;
				 Statement stmt = null;
				 try{
				    //STEP 2: Register JDBC driver
				    Class.forName("com.mysql.jdbc.Driver");

				    //STEP 3: Open a connection
				    System.out.println("Connecting to database...");
				    conn = DriverManager.getConnection(DB_URL,USER,PASS);

				    //STEP 4: Execute a query
				    System.out.println("Creating statement...");
				    stmt = conn.createStatement();
				 
			 String sql;
			 sql="INSERT INTO inputData VALUES('"+username+"', '"+station+"', '"+sqlDate+"');";
		    
		    stmt.executeUpdate(sql);

		    
		    stmt.close();
		    conn.close();
		 }catch(SQLException se){
		    //Handle errors for JDBC
		    se.printStackTrace();
		 }catch(Exception e){
		    //Handle errors for Class.forName
		    e.printStackTrace();
		 }finally{
		    //finally block used to close resources
		    try{
		       if(stmt!=null)
		          stmt.close();
		    }catch(SQLException se2){
		    }// nothing we can do
		    try{
		       if(conn!=null)
		          conn.close();
		    }catch(SQLException se){
		       se.printStackTrace();
		    }//end finally try
		 }//end try
		 System.out.println("Goodbye!");
		}//end main
		
}//end FirstExample



