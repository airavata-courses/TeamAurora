package com.sg.aurora.dataingestorworker.rest.resources;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.sg.aurora.dataingestorworker.rabbitmq.producer.MessageSender;
import com.sg.aurora.common.utils.*;


public class DataIngestorController {
	private String APIGATEWAY_DATAINGESTOR_QUEUE = "APIGATEWAY_DATAINGESTOR_QUEUE";
	
	private void startWorkerNode(){
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("rabbitmqhost");
	    Connection connection;
		try {
			connection = factory.newConnection();
		
			final Channel channel = connection.createChannel();
	
		    boolean durable = true;
		    channel.queueDeclare(APIGATEWAY_DATAINGESTOR_QUEUE, durable, false, false, null);
		    System.out.println("DATAINGESTOR :: Consumer started");
	
		    Consumer consumer = new DefaultConsumer(channel) {
		      @Override
		      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
		          throws IOException {
		    	  try {
		    		//String incomingMessageStr = (String)SerializerDeserializer.deserialize(body);
		    		ObjectMapper mapper = new ObjectMapper();
		    		//String incomingMessageStr = mapper.readValue(body, String.class);
		    		String incomingMessageStr = new String(body, "UTF-8");
		    		URLFormData incomingMessageObj = mapper.readValue(incomingMessageStr, URLFormData.class);
		    		System.out.println("Message received from " + incomingMessageObj.getFromService());
		    		
		    		URLData result = getURL(incomingMessageObj);
		    		result.setFromService(Service.DATAINGESTOR);
		    		result.setToService(Service.APIGATEWAY);
		    		result.setUserId(incomingMessageObj.getUserId());
		    		result.setRequestId(incomingMessageObj.getRequestId());		    		
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
		    channel.basicConsume(APIGATEWAY_DATAINGESTOR_QUEUE, autoAck, consumer);
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private URLData getURL(URLFormData urlFormData) throws ParseException, SQLException {
		URLData urlData=new URLData();
		PostgreSQLDB postDB = new PostgreSQLDB();
		
		int user_id=urlFormData.getUserId();
		int request_id=urlFormData.getRequestId();
		String service_name="dataIngestor";
		
		String formDate=urlFormData.getDate();
		String formTime=urlFormData.getTime();
		
		String dateArr[]=formDate.split("/");
		String timeArr[]=formTime.split(":");

		String year=dateArr[0];
		String month=dateArr[1];
		String date=dateArr[2];
		
		String hours=timeArr[0];
		String minutes=timeArr[1];
		String seconds=timeArr[2];
		String stationName=urlFormData.getStationName();
		
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(urlFormData);
			String resultURL="https://aws.amazon.com/noaa-big-data/nexrad/"+urlFormData.getDate()
			+"/"+stationName+"/"+stationName+year+month+date+"_"+hours+minutes+seconds+"_V06";
			
			postDB.loggingToDB(user_id,request_id,service_name,jsonInString,resultURL);
			urlData.setUrl("https://noaa-nexrad-level2.s3.amazonaws.com/2016/09/17/KIND/KIND20160917_000007_V06");
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return urlData;
	}
	
	public static void main(String args[]) {
		//Starting consumer
		DataIngestorController node = new DataIngestorController();
		node.startWorkerNode();

	}

}
