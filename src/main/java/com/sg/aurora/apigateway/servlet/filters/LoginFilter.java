package com.sg.aurora.apigateway.servlet.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
  
public class LoginFilter implements Filter{  
  
	public void init(FilterConfig arg0) throws ServletException 
	{
	}  
	      
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {  	          
		String path = ((HttpServletRequest) req).getRequestURI();
		if (path.startsWith("/apigateway/api/login")) {
		    chain.doFilter(req, resp); // Just continue chain.
		} 
		else {
			HttpServletRequest request = (HttpServletRequest) req;
		    HttpServletResponse response = (HttpServletResponse) resp;
		    HttpSession session = request.getSession(false);
		    String loginURI = request.getContextPath() + "/jsp/login.jsp";
		
		    boolean loggedIn = session != null && session.getAttribute("USERNAME") != null;
		    boolean loginRequest = request.getRequestURI().equals(loginURI);
		
		    if (loggedIn || loginRequest) {
		        chain.doFilter(request, response);
		    } else {
		    	response.sendRedirect(loginURI);
		    }
		}
	}  
    
	public void destroy() 
	{
	}  
  
}  