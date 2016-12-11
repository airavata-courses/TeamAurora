package com.sg.aurora.forecasttriggerworker.rabbitmq.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.sg.aurora.common.utils.*;
import com.sg.aurora.forecasttriggerworker.rest.MyProperties;

public class MessageSender {
	public static void sendMessageToAPIGateway(ClusterData clusterData) throws IOException, TimeoutException{
		MyProperties properties=new MyProperties();
		String rabbitMQhost=properties.readPropertiesFile("rabbitMQ.host");
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(rabbitMQhost);
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    //Method below handles load balancing amongst multiple containers of the same service.
	    int prefetchCount = 1;
	    channel.basicQos(prefetchCount);
	    
	    boolean durable = true;
	    channel.queueDeclare(Constants.APIGATEWAY_ALLSERVICES_QUEUE, durable, false, false, null);
	    ObjectMapper objMapper=new ObjectMapper();
	    String outgoingStr = objMapper.writeValueAsString(clusterData);
	    //channel.basicPublish("", APIGATEWAY_ALLSERVICES_QUEUE, null, SerializerDeserializer.serialize(outgoingStr));
	    channel.basicPublish("", Constants.APIGATEWAY_ALLSERVICES_QUEUE, MessageProperties.PERSISTENT_TEXT_PLAIN, outgoingStr.getBytes());
	    System.out.println(" Message Sent ");
	    
	    channel.close();
	    connection.close();
	}
	
}
