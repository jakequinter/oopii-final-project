<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employees</title>
</head>
<body>
<%@ include file="nav.jsp" %>
<h1>Employees</h1>

<table class="table">
  <thead>
    <tr>
      <th scope="col">First Name</th>
      <th scope="col">Last Name</th>
      <th scope="col">Address</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${ employees }" var="e">
  <tr>
  <td><a href="edit?id=${ e.employeeID }">${ e.firstName }</a></td>
  <td> ${ e.lastName }</td>
  <td> ${ e.address }</td>
  </tr>

</c:forEach>
  
  </tbody>
</table>



</body>
</html>