<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee: ${ employee.firstName } ${ employee.lastName }</title>
</head>
<body>
<%@ include file="nav.jsp" %>
<h1>Employee: ${ employee.firstName } ${ employee.lastName }</h1>
<ul>
<li>First Name: ${ employee.firstName }</li>
<li>Last Name: ${ employee.lastName }</li>
<li>Employee ID: ${ employee.employeeID }</li>
<li>Position: ${ employee.address }</li>
</ul>
</body>
</html>