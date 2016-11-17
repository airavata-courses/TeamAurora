package com.sg.aurora.apigateway.rabbitmq.consumer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.sg.aurora.apigateway.rabbitmq.producer.MessageSender;
import com.sg.aurora.apigateway.rest.service.RequestService;
import com.sg.aurora.apigateway.util.MyProperties;
import com.sg.aurora.common.utils.ClusterData;
import com.sg.aurora.common.utils.Constants;
import com.sg.aurora.common.utils.SerializerDeserializer;
import com.sg.aurora.common.utils.Service;
import com.sg.aurora.common.utils.URLData;


public class MessageReceiver {
	public static void startReceiver() throws IOException{
		MyProperties properties= new MyProperties();
		String RabbitMQhost=properties.readPropertiesFile("rabbitMQ.host");
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(RabbitMQhost);
	    Connection connection;
		try {
			connection = factory.newConnection();		
		    final Channel channel = connection.createChannel();
	
		    boolean durable = true;
		    channel.queueDeclare(Constants.APIGATEWAY_ALLSERVICES_QUEUE, durable, false, false, null);
		    System.out.println(" APIGATEWAY :: Waiting for messages from all microservices");
	
		    Consumer consumer = new DefaultConsumer(channel) {
		      @Override
		      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
		          throws IOException {
		    	  try {
		    		  //String incomingMessageStr = (String)deserializePythonString(body);
		    		  //String incomingMessageStr = (String)SerializerDeserializer.deserialize(body);
		    		  ObjectMapper mapper = new ObjectMapper();
		    		  //String incomingMessageStr = mapper.readValue(body, String.class);
		    		  String incomingMessageStr = new String(body, "UTF-8");
		    		  JSONObject jsonObj = new JSONObject(incomingMessageStr);
		    		  Service senderService = Service.valueOf(jsonObj.getString("fromService"));
		    		  System.out.println("APIGATEWAY :: Message received from " + senderService);
		    		  
		    		  String outgoingMessageStr = "";
		    		  Service receivingService = Service.APIGATEWAY;
		    		  String QUEUE_NAME="";
		    		  RequestService requestService = new RequestService();
		    		  Integer requestId= (Integer)((jsonObj.get("requestId")));
		    		  switch(senderService){
			    		  case DATAINGESTOR:{
			    			  requestService.updateServiceStatus(requestId, "API Gateway after DataIngestor");
			    			  receivingService = Service.STORMDETECTOR;
			    			  ObjectMapper objMapper = new ObjectMapper();
			    			  URLData incomingMessageObj = objMapper.readValue(incomingMessageStr, URLData.class);
			    			  incomingMessageObj.setFromService(Service.APIGATEWAY);
			    			  incomingMessageObj.setToService(Service.STORMDETECTOR);
			    			  
			    			  outgoingMessageStr = objMapper.writeValueAsString(incomingMessageObj);
			    			  QUEUE_NAME = Constants.APIGATEWAY_STORMDETECTOR_QUEUE;
			    			  
			    			  break;
			    		  }
			    		  case STORMDETECTOR:{
			    			  requestService.updateServiceStatus(requestId, "API Gateway after Storm Detector");
			    			  receivingService = Service.STORMCLUSTERING;
			    			  outgoingMessageStr= incomingMessageStr;
			    			  
			    			  QUEUE_NAME = Constants.APIGATEWAY_STORMCLUSTERING_QUEUE;
			    			  break;
			    		  }
			    		  case STORMCLUSTERING:{
			    			  requestService.updateServiceStatus(requestId, "API Gateway after Storm Clustering");
			    			  ObjectMapper objMapper = new ObjectMapper();
			    			  
			    			  
			    			  String val = String.valueOf(jsonObj.getString("val"));
			    			  receivingService = Service.FORECASTTRIGGER;
			    			  ClusterData cdObj=new ClusterData();
			    			  cdObj.setFromService(Service.APIGATEWAY);
			    			  cdObj.setToService(Service.FORECASTTRIGGER);
			    			  cdObj.setClusters(val);
			    			  cdObj.setUserId(Integer.valueOf(jsonObj.getInt("userId")));
			    			  cdObj.setRequestId(Integer.valueOf(jsonObj.getInt("requestId")));
			    			  outgoingMessageStr = objMapper.writeValueAsString(cdObj);
			    			  QUEUE_NAME = Constants.APIGATEWAY_FORECASTTRIGGER_QUEUE;
			    			  break;
			    		  }
			    		  case FORECASTTRIGGER:{
			    			  requestService.updateServiceStatus(requestId, "API Gateway after Forecast Trigger","Completed");
			    			  //QUEUE_NAME = Constants.APIGATEWAY_FORECASTTRIGGER_QUEUE;
			    			  boolean stormExist = jsonObj.getBoolean("stormExists");
			    			  System.out.println("Storm Exists: " + stormExist);
			    			  break;
			    		  }
			    		  default:
		    		  }
		    		  
		    		  //Call an appropriate service based on the service name.
		    		  if(!senderService.equals(Service.FORECASTTRIGGER)){
		    			  MessageSender.sendMessageToMicroServices(outgoingMessageStr, receivingService, QUEUE_NAME);
		    		  }
		    		  
				  System.out.println("Done processing the job from microservice.");
		    	          channel.basicAck(envelope.getDeliveryTag(), false);
				  
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	  
		      }
		    };
		    boolean autoAck = false;
		    channel.basicConsume(Constants.APIGATEWAY_ALLSERVICES_QUEUE, autoAck, consumer);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Object deserializePythonString(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readUTF();
            
    }
}
