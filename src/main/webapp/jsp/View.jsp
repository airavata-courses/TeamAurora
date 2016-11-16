<%@page import="com.sg.aurora.apigateway.rest.model.GetStatus"%>
<%@page import="com.sg.aurora.apigateway.rest.service.RequestService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Team Aurora</title>
</head>
<body>
<table border="1" width="60%" align="center">

<tr bgcolor="#76D7C4" align="center">
<td><b>Request Id</b></td>
<td><b>Service Name</b></td>
<td><b>Status</b></td>
<td><b>Start Time</b></td>
</tr>
<c:forEach items="${list}" var="current">
<!--  <tr bgcolor="#DEB887">-->
<tr align="center">

<td>${current.requestId} </td>
<td> ${current.serviceName} </td>
<td>${current.status} </td>
<td>${current.startTime} </td>

</tr>
</c:forEach>
            </table>
</body>
</html>

