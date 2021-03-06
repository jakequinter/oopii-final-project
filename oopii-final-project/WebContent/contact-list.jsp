<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Address Book | Contacts</title>
  </head>
<body>
    <%@ include file="/WEB-INF/nav.jsp" %>
    <div class="row m-5">
    	<div class="container">
        	<h1 class="text-center">Contacts</h1>
        	<hr />
        	<div class="container text-left pt-2" >
          		<a href="<%=request.getContextPath()%>/new" class="btn btn-primary">Add New Contact</a>
        	</div>
        	<br />

        	<table class="table table-center">
          		<thead class="thead-dark">
            		<tr>
              			<th>ID</th>
              			<th>First Name</th>
              			<th>Last Name</th>
              			<th>Edit | Delete</th>
            		</tr>
          		</thead>
          		<tbody>
            		<c:forEach var="contact" items="${contacts}">
              			<tr>
	                		<td><c:out value="${contact.contactId}" /></td> 
	                		<td><c:out value="${contact.firstName}" /></td>
	                		<td><c:out value="${contact.lastName}" /></td>
	                		<td>
	                  			<a href="edit?contactId=<c:out value='${contact.contactId}' />">
	                    			<span style="color: #686868;">
	                      				<i class="fas fa-edit"></i>
	                    			</span>
	                  			</a>
	                  			&nbsp;&nbsp;&nbsp;&nbsp;
	                  			<a href="delete?contactId=<c:out value='${contact.contactId}'/>">
	                    			<span style="color: #FF5E5E;">
	                      				<i class="fas fa-trash-alt"></i>
	                    			</span>
	                  			</a>
	                		</td>
              			</tr>	
            		</c:forEach>
          		</tbody>
        	</table>
      	</div>
	</div>
</body>
</html>
