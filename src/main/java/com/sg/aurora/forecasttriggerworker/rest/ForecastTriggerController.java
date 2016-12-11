package com.sg.aurora.forecasttriggerworker.rest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

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
import com.sg.aurora.common.utils.apacheaurora.bean.JobConfigurationBean;
import com.sg.aurora.common.utils.apacheaurora.bean.ProcessBean;
import com.sg.aurora.common.utils.apacheaurora.bean.ResourceBean;
import com.sg.aurora.common.utils.apacheaurora.bean.ResponseBean;
import com.sg.aurora.common.utils.apacheaurora.bean.TaskConfigBean;
import com.sg.aurora.common.utils.apacheaurora.sdk.ExecutorConfig;
import com.sg.aurora.common.utils.apacheaurora.sdk.Identity;
import com.sg.aurora.common.utils.apacheaurora.sdk.JobConfiguration;
import com.sg.aurora.common.utils.apacheaurora.sdk.JobKey;
import com.sg.aurora.common.utils.apacheaurora.sdk.Resource;
import com.sg.aurora.common.utils.apacheaurora.sdk.Response;
import com.sg.aurora.common.utils.apacheaurora.sdk.TaskConfig;
import com.sg.aurora.common.utils.apacheaurora.thrift.client.AuroraThriftClient;
import com.sg.aurora.common.utils.apacheaurora.utils.AuroraThriftClientUtil;
import com.sg.aurora.common.utils.apacheaurora.utils.Constants;
import com.sg.aurora.common.utils.apacheaurora.utils.ResponseResultType;

//

public class ForecastTriggerController {
	
	private static Properties properties = new Properties();
	
	private String APIGATEWAY_FORECASTTRIGGER_QUEUE = "APIGATEWAY_FORECASTTRIGGER_QUEUE";
	
	private void startWorkerNode() throws IOException{
		
		MyProperties properties=new MyProperties();
		String rabbitMQhost=properties.readPropertiesFile("rabbitMQ.host");
		ConnectionFactory factory = new ConnectionFactory();
		
		
	    factory.setHost(rabbitMQhost);
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
		    		PostgreSQLDB postDB=new PostgreSQLDB();
		    		postDB.updateServiceStatus(incomingMessageObj.getRequestId(), "Forecast Trigger");
		    		System.out.println("Message received from " + incomingMessageObj.getFromService());
		    		
		    		ClusterData result=results(incomingMessageObj);
		    		result.setFromService(Service.FORECASTTRIGGER);
		    		result.setToService(Service.APIGATEWAY);
		    		
		    		//Running job on Mesos Cluster
		    		runJobOnMesos(incomingMessageObj.getRequestId());
		    		
		    		MessageSender.sendMessageToAPIGateway(result);
		    		
				channel.basicAck(envelope.getDeliveryTag(), false);  
				  
				} catch (Exception e) {
					e.printStackTrace();
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
	
	private void runJobOnMesos(Integer requestId) throws Exception{
		AuroraThriftClient client = AuroraThriftClient.getAuroraThriftClient(Constants.AURORA_SCHEDULER_PROP_FILE);
		
		JobKey jobKey  = new JobKey( "team-aurora", "devel","job_aurora_" + requestId);
		Identity owner = new Identity("team-aurora");
		TaskConfig taskConfig = new TaskConfig();
		taskConfig.setJob(jobKey);
		taskConfig.setOwner(owner);
		taskConfig.setIsService(false); 
		taskConfig.setNumCpus(0.25); 
		taskConfig.setRamMb(200);
		taskConfig.setDiskMb(500); 
		taskConfig.setPriority(0);
		taskConfig.setMaxTaskFailures(1);
		
		Set<Resource> resourceSet = new HashSet<>();
		
		// add numCpus
		Resource resource = new Resource();
		resource.setNumCpus(0.25);
		resourceSet.add(resource);
		
		// add diskMb
		resource = new Resource();
		resource.setDiskMb(500);
		resourceSet.add(resource);
		
		// add ramMb
		resource = new Resource();
		resource.setRamMb(200);
		resourceSet.add(resource);
		
		taskConfig.setResources(resourceSet);
		
		//ProcessBean proc1 = new ProcessBean("process_1", "docker ps", false);
		//ProcessBean proc2 = new ProcessBean("process_2", "docker ps", false);
		
		
		String containerName = "aurora-ncarwrfsandy-" + requestId;
		
		ProcessBean proc1 = new ProcessBean("process_1", "docker run -i --volumes-from wpsgeog --volumes-from wrfinputsandy -v ~/wrfoutput:/wrfoutput --name "+ containerName + " bigwxwrf/ncar-wrf /wrf/run-wrf", false);
		ProcessBean proc2 = new ProcessBean("process_2", "docker run -i --rm=true -v ~/wrfoutput:/wrfoutput --name postproc bigwxwrf/ncar-ncl", false);
		Set<ProcessBean> processes = new HashSet<>();
		processes.add(proc1);
		processes.add(proc2);
		
		ResourceBean resources = new ResourceBean(0.25, 500, 200);
		
		
		TaskConfigBean taskConfigBean = new TaskConfigBean("task_aurora_" + requestId, processes, resources);
		
		
		
		JobConfigurationBean jobConfigBean = new JobConfigurationBean(jobKey, owner, taskConfigBean, "example");
		// construct the executor config for this job

		taskConfig.setExecutorConfig(getExecutorConfig(getExecutorConfigJson(jobConfigBean)));

		// construct the job configuration
		JobConfiguration jobConfig = new JobConfiguration(jobKey, owner, null, taskConfig, 1);
		
		
		ResponseBean response = null;
		
		
		//client.getAuroraSchedulerManagerClient().
		
		Response createJobResponse = client.getAuroraSchedulerManagerClient().createJob(jobConfig);
		response = AuroraThriftClientUtil.getResponseBean(createJobResponse, ResponseResultType.CREATE_JOB);
	}
	
	private static ExecutorConfig getExecutorConfig(String exeConfigJson) throws Exception {
		// TODO Auto-generated method stub
		
		ExecutorConfig exeConfig = null;
		
		try {
			properties.load(AuroraThriftClientUtil.class.getClassLoader().getResourceAsStream(Constants.AURORA_SCHEDULER_PROP_FILE));
			String executorName = properties.getProperty(Constants.AURORA_EXECUTOR_NAME);
			
			// create the executor config
			if(exeConfigJson != null) {
				exeConfig = new ExecutorConfig(executorName, exeConfigJson);
			} else {
				throw new Exception("Aurora Executor Config Data is NULL!");
			}
		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			throw ex;
		}
		
		return exeConfig;
		
		
		//return null;
	}

	private static String getExecutorConfigJson(JobConfigurationBean jobConfig) throws Exception  {
		// TODO Auto-generated method stub
		
		
		String exeConfigJson = null;
		try {
			String template = IOUtils.toString(AuroraThriftClientUtil.class.getClassLoader()
					.getResourceAsStream("executor-config-template.json"), Charset.defaultCharset());
			
			JSONObject exeConfig = new JSONObject(template);
			if(exeConfig != null) {
				exeConfig.put("environment", jobConfig.getJob().getEnvironment());
				exeConfig.put("name", jobConfig.getJob().getName());
				exeConfig.put("role", jobConfig.getJob().getRole());
				exeConfig.put("cluster", jobConfig.getCluster());
				exeConfig.put("max_task_failures", jobConfig.getMaxTaskFailures());
				exeConfig.put("service", jobConfig.isService());
				
				exeConfig.getJSONObject("task").put("name", jobConfig.getTaskConfig().getTaskName());
				
				// add task resources
				exeConfig.getJSONObject("task").getJSONObject("resources")
					.put("cpu", jobConfig.getTaskConfig().getResources().getNumCpus());
				
				exeConfig.getJSONObject("task").getJSONObject("resources")
				.put("disk", jobConfig.getTaskConfig().getResources().getDiskMb() * 1024 * 1024);
				
				exeConfig.getJSONObject("task").getJSONObject("resources")
				.put("ram", jobConfig.getTaskConfig().getResources().getRamMb() * 1024 * 1024);
				
				// iterate over all processes
				for(ProcessBean process : jobConfig.getTaskConfig().getProcesses()) {
					// add process to constraints
					exeConfig.getJSONObject("task")
						.getJSONArray("constraints")
						.getJSONObject(0)
						.getJSONArray("order").put(process.getName());
					
					// define the process json
					JSONObject processJson = new JSONObject();
					processJson.put("final", process.isFinal())
						.put("daemon", process.isDaemon())
						.put("name", process.getName())
						.put("ephemeral", process.isEphemeral())
						.put("max_failures", process.getMax_failures())
						.put("min_duration", process.getMin_duration())
						.put("cmdline", process.getCmdLine());
					
					// add process json to list
					exeConfig.getJSONObject("task")
					.getJSONArray("processes").put(processJson);
				}
				
				// convert json object to string
				exeConfigJson = exeConfig.toString();
			}
 		} catch(Exception ex) {
			//logger.error(ex.getMessage(), ex);
			throw ex;
		}
		return exeConfigJson;
		
		//return null;
	}
	
	public static void main(String args[]) throws IOException {
		//Starting consumer
		ForecastTriggerController node = new ForecastTriggerController();
		node.startWorkerNode();

	}
    
}
