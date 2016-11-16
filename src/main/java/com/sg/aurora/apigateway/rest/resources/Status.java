package com.sg.aurora.apigateway.rest.resources;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sg.aurora.apigateway.rest.model.GetStatus;
import com.sg.aurora.apigateway.rest.service.RequestService;

@WebServlet("/api/View.jsp")
public class Status extends HttpServlet{

  	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		HttpSession session = request.getSession();
  		int userId = (Integer)session.getAttribute("USERID");
        RequestService requestService = new RequestService();
        ArrayList<GetStatus> userRequests=requestService.getStatus(userId);
        request.setAttribute("list", userRequests);
        getServletContext().getRequestDispatcher("/jsp/View.jsp").forward(request, response);
    }
}
