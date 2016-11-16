package com.sg.aurora.forecasttriggerworker.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.sg.aurora.common.utils.ClusterData;
import com.sg.aurora.common.utils.Service;

import com.sg.aurora.forecasttriggerworker.rabbitmq.producer.MessageSender;


//

public class ForecastTriggerController {
	
private String APIGATEWAY_FORECASTTRIGGER_QUEUE = "APIGATEWAY_FORECASTTRIGGER_QUEUE";
	
	private void startWorkerNode(){
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection;
		try {
			connection = factory.newConnection();
		
			final Channel channel = connection.createChannel();
	
		    boolean durable = true;
		    channel.queueDeclare(APIGATEWAY_FORECASTTRIGGER_QUEUE, durable, false, false, null);
		    System.out.println("FORECASTTRIGGER :: Consumer started");
	
		    Consumer consumer = new DefaultConsumer(channel) {
		      @Override
		      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
		          throws IOException {
		    	  try {
		    		//String incomingMessageStr = (String)SerializerDeserializer.deserialize(body);
		    		ObjectMapper mapper = new ObjectMapper();
		    		//String incomingMessageStr = mapper.readValue(body, String.class);
		    		String incomingMessageStr = new String(body, "UTF-8");
		    		ClusterData incomingMessageObj = mapper.readValue(incomingMessageStr, ClusterData.class);
		    		System.out.println("Message received from " + incomingMessageObj.getFromService());
		    		
		    		ClusterData result=results(incomingMessageObj);
		    		result.setFromService(Service.FORECASTTRIGGER);
		    		result.setToService(Service.APIGATEWAY);
		    		
		    		MessageSender.sendMessageToAPIGateway(result);
		    		 
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	  finally{
		    		  channel.basicAck(envelope.getDeliveryTag(), false);
		    	  }
		      }
		    };
		    boolean autoAck = false;
		    channel.basicConsume(APIGATEWAY_FORECASTTRIGGER_QUEUE, autoAck, consumer);
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

     
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
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clusterData;
    }
	
	public static void main(String args[]) {
		//Starting consumer
		ForecastTriggerController node = new ForecastTriggerController();
		node.startWorkerNode();

	}
    
}
