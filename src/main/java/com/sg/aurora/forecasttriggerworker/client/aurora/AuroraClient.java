package com.sg.aurora.forecasttriggerworker.client.aurora;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.aurora.gen.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sg.aurora.forecasttriggerworker.client.aurora.utils.*;
import com.sg.aurora.forecasttriggerworker.thrift.client.AuroraThriftClient;
import com.sg.aurora.forecasttriggerworker.bean.*;


/**
 * The Class AuroraClient.
 */
public class AuroraClient {
	
	/** The aurora scheduler client. */
	private static ReadOnlyScheduler.Client auroraSchedulerClient;
	
	/** The properties. */
	private static Properties properties = new Properties();
	
	private static AuroraClient thriftClient = null;
	

	public static void createJob() throws Exception {
		JobKeyBean jobKey = new JobKeyBean("devel", "centos", "test_job");
		IdentityBean owner = new IdentityBean("centos");
		
		ProcessBean proc1 = new ProcessBean("process_1", "echo 'hello_world_1'", false);
		ProcessBean proc2 = new ProcessBean("process_2", "echo 'hello_world_2'", false);
		Set<ProcessBean> processes = new HashSet<>();
		processes.add(proc1);
		processes.add(proc2);
		
		ResourceBean resources = new ResourceBean(0.1, 8, 1);
		
		TaskConfigBean taskConfig = new TaskConfigBean("task_hello_world", processes, resources);
		JobConfigBean jobConfig = new JobConfigBean(jobKey, owner, taskConfig, "example");
		
		String executorConfigJson = AuroraThriftClientUtil.getExecutorConfigJson(jobConfig);
		System.out.println(executorConfigJson);
		
		AuroraThriftClient client = AuroraThriftClient.getAuroraThriftClient(Constants.AURORA_SCHEDULER_PROP_FILE);
		ResponseBean response = client.createJob(jobConfig);
		System.out.println(response);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		 try {
			properties.load(AuroraClient.class.getClassLoader().getResourceAsStream(Constants.AURORA_SCHEDULER_PROP_FILE));
			String auroraHost = properties.getProperty(Constants.AURORA_SCHEDULER_HOST);
			String auroraPort = properties.getProperty(Constants.AURORA_SCHEDULER_PORT);
			auroraSchedulerClient = AuroraSchedulerClientFactory.createReadOnlySchedulerClient(MessageFormat.format(Constants.AURORA_SCHEDULER_CONNECTION_URL, auroraHost, auroraPort));
			
			// get jobs summary
//			AuroraClientSample.getJobSummary(auroraSchedulerClient);
			
			// create sample job
//			AuroraClientSample.createJob();
			AuroraThriftClient client = AuroraThriftClient.getAuroraThriftClient(Constants.AURORA_SCHEDULER_PROP_FILE);
			ResponseBean response = client.getPendingReasonForJob(new JobKeyBean("devel", "centos", "hello_pending"));
			System.out.println(response);
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}

	
}