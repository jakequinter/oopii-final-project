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

<%-- <table class="table">
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
  <td><a href="edit?id=${ e.id }">${ e.firstName }</a></td>
  <td> ${ e.lastName }</td>
  <td> ${ e.address }</td>
  </tr>

</c:forEach>
  
  </tbody>
</table> --%>
 
 <div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Contacts</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Contact</a>
			</div>
			<br>
			<table class="table">
				<thead>
					<tr>
						<!-- <th>ID</th>  -->
						<th>First Name2</th>
						<th>Last Name</th>
						<th>Address</th>
						<!-- th>Actions</th> -->
					</tr>
				</thead>
				<tbody>
				<%-- <c:forEach items="${ employees }" var="e">
				  <tr>
				  <td><a href="edit?id=${ e.employeeID }">${ e.firstName }</a></td>
				  <td> ${ e.lastName }</td>
				  <td> ${ e.address }</td>
							  <td><a href="edit?id=<c:out value='${contact.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${contact.id}' />">Delete</a></td> 
				  </tr>
				</c:forEach> --%>
					<!--   for (Todo todo: todos) {  -->
					  <c:forEach var="contact" items="${employees}">

						<tr>
							 <%-- <td><c:out value="${contact.id}" /></td> --%>
							<td><c:out value="${contact.firstName}" /></td>
							<td><c:out value="${contact.lastName}" /></td>
							<td><c:out value="${contact.address}" /></td>
							  <td><a href="edit?id=<c:out value='${contact.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${contact.id}' />">Delete</a></td> 
						</tr>
					</c:forEach> 
					 <!-- } -->
				</tbody> 

			</table>
		</div>
	</div> 


</body>
</html>