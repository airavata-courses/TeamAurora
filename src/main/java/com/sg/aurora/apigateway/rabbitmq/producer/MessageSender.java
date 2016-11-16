package com.sg.aurora.apigateway.rabbitmq.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.sg.aurora.common.utils.SerializerDeserializer;
import com.sg.aurora.common.utils.Service;

public class MessageSender {

	public static void sendMessageToMicroServices(String message, Service toServiceName, String queueName) throws IOException, TimeoutException{
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("35.161.35.175");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    //Method below handles load balancing amongst multiple containers of the same service.
	    int prefetchCount = 1;
	    channel.basicQos(prefetchCount);
	    
	    boolean durable = true;
	    channel.queueDeclare(queueName, durable, false, false, null);
	    //channel.basicPublish("", queueName, null, SerializerDeserializer.serialize(message));
	    channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
    	System.out.println("APIGATEWAY :: Message Sent to " + toServiceName);
	    
	    channel.close();
	    connection.close();
	}
	
}
