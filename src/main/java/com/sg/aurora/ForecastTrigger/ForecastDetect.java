package com.sg.aurora.ForecastTrigger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/detected")
public class ForecastDetect {

    @POST
    @Path("/storm")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON) 
	public ClusterData results(ClusterData clusterData) {
    	try {
    		PostgreSQLDB postDB = new PostgreSQLDB();
			
	    	String clusters = clusterData.getClusters();
	    	int user_id = clusterData.getUserId();
			int request_id = clusterData.getRequestId();
			String service_name = "runWeatherForecast";
			
			System.out.println("Deciding on Storm");
			Random rand = new Random();
			int val = rand.nextInt();
			
			if(val > 1){
				postDB.loggingToDB(user_id,request_id,service_name, clusters, "Storm Exist");
				clusterData.setStormExists(true);
			}		
			else{
				postDB.loggingToDB(user_id,request_id,service_name, clusters,"No Storm");
				clusterData.setStormExists(false);
			}
    	} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Code added to add more work for load test.
		int[] a=new int[200000];
		long sum=0;
		for(int i=0;i<200000;i++)
		{
			a[i]=i;
			sum+=i;
			sum-=i;
		}

		for(int i=0;i<200000;i++)
		{
			a[i]=10;
		}
		
		return clusterData;
    }
    
}
