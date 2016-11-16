package com.sg.aurora.dataingestorworker.rabbitmq.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.sg.aurora.common.utils.*;

public class MessageSender {
	public static void sendMessageToAPIGateway(URLData urlData) throws IOException, TimeoutException{
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("35.161.35.175");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    //Method below handles load balancing amongst multiple containers of the same service.
	    int prefetchCount = 1;
	    channel.basicQos(prefetchCount);
	    
	    boolean durable = true;
	    channel.queueDeclare(Constants.APIGATEWAY_ALLSERVICES_QUEUE, durable, false, false, null);
	    ObjectMapper objMapper=new ObjectMapper();
	    String outgoingStr = objMapper.writeValueAsString(urlData);
	    //channel.basicPublish("", APIGATEWAY_ALLSERVICES_QUEUE, null, SerializerDeserializer.serialize(outgoingStr));
	    channel.basicPublish("", Constants.APIGATEWAY_ALLSERVICES_QUEUE, MessageProperties.PERSISTENT_TEXT_PLAIN, outgoingStr.getBytes());
	    System.out.println(" Message Sent ");
	    
	    channel.close();
	    connection.close();
	}
	
}
