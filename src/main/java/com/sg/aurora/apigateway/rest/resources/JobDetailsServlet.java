package com.sg.aurora.apigateway.rest.resources;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sg.aurora.apigateway.rest.model.GetStatus;
import com.sg.aurora.apigateway.rest.model.Task;
import com.sg.aurora.apigateway.rest.service.RequestService;
import com.sg.aurora.common.utils.apacheaurora.bean.JobDetailsResponseBean;
import com.sg.aurora.common.utils.apacheaurora.sdk.JobKey;
import com.sg.aurora.common.utils.apacheaurora.sdk.ScheduledTask;
import com.sg.aurora.common.utils.apacheaurora.sdk.TaskQuery;
import com.sg.aurora.common.utils.apacheaurora.thrift.client.AuroraThriftClient;
import com.sg.aurora.common.utils.apacheaurora.utils.AuroraThriftClientUtil;
import com.sg.aurora.common.utils.apacheaurora.utils.ResponseResultType;

@WebServlet("/api/jobdetails.jsp")
public class JobDetailsServlet  extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		
  		JobDetailsResponseBean detailsReponse = null;
  		ArrayList<Task> result = new ArrayList<Task>();
  		String host="";
  		try {
  			System.out.println("Inside job details servlet");
  			AuroraThriftClient client = AuroraThriftClient.getAuroraThriftClient(com.sg.aurora.common.utils.apacheaurora.utils.Constants.AURORA_SCHEDULER_PROP_FILE);
  			String jobName = "job_aurora_" + request.getParameter("requestId");
  			JobKey jobKey  = new JobKey( "team-aurora", "devel",jobName);
  			
  			Set<JobKey> jobKeySet = new HashSet<JobKey>();
  			jobKeySet.add(jobKey);
  			
  			TaskQuery query = new TaskQuery();
  			query.setJobKeys(jobKeySet);
  			
  			com.sg.aurora.common.utils.apacheaurora.sdk.Response jobDetailsResponse = client.getReadOnlySchedulerClient().getTasksStatus(query);
  			detailsReponse = (JobDetailsResponseBean) AuroraThriftClientUtil.getResponseBean(jobDetailsResponse, ResponseResultType.GET_JOB_DETAILS);
  			
  			for(ScheduledTask s : detailsReponse.getTasks())
  			{
  				host=s.assignedTask.slaveHost;
  				if(host.equals("sga-mesos-slave-1"))
  				{
  					host="52.53.179.0";
  				}
  				else if(host.equals("sga-mesos-slave-2"))
  				{
  					host="54.215.219.32";
  				}
  			 	result.add(new Task(s.getAssignedTask().getTaskId(), s.getStatus().toString(), host)); 
  				//System.out.println( + "  " + );
  			}
  			
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		request.setAttribute("list", result);
  		request.setAttribute("host", host);
  		getServletContext().getRequestDispatcher("/jsp/jobdetails.jsp").forward(request, response);
    }
}




