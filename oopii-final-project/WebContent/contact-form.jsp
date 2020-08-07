
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<body>
    <%@ include file="/WEB-INF/nav.jsp" %>
<div class="container col-md-5" style="margin-top: 50px;">
		<div class="card" >
			<div class="card-body"">
		 		<c:if test="${contact != null}"> 
					<form id="contactForm" action="update">
				</c:if>  
				<c:if test="${contact == null}">  
					<form id="contactForm" action="insert">
 				</c:if>

				<caption>
					<h2 style="text-align: center;">
						<c:if test="${contact != null}">
            			${contact.fullName}
            		</c:if>
						<c:if test="${contact == null}">
            			Add New Contact
            		</c:if>
					</h2>
				</caption>

				<c:if test="${contact != null}">
					<input type="hidden" name="contactId" value="<c:out value='${contact.contactId}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>First Name</label> <input type="text" id="name"
						value="<c:out value='${contact.firstName}' />" class="form-control"
						name="firstName">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Last Name</label> <input type="text" id="name"
						value="<c:out value='${contact.lastName}' />" class="form-control"
						name="lastName">
				</fieldset>
				
				<c:if test="${contact != null}"> 
				<h5>Addresses</h5>
                <c:forEach var="address" items="${addresses}">
                
                  <div class="input-group" style="margin-bottom: 15px;">
					  <input type="text" readonly class="form-control" value='<c:out value='${address.addressLine1}' /> <c:out value='${address.addressLine2}' />, <c:out value='${address.city}' />, <c:out value='${address.state}' />, <c:out value='${address.postalCode}' />' >
					  <div class="input-group-append" id="button-addon4">
					    <a class="btn btn-outline-secondary" href="edit-address?fkAddressContactId=<c:out value='${contact.contactId}' />&addressId=<c:out value='${address.addressId}' />" role="button">Edit</a>
					    <a class="btn btn-outline-danger" href="delete-address?addressId=<c:out value='${address.addressId}' />&contactId=<c:out value='${contact.contactId}' />">
					      Delete
                  		</a>
					  </div>
                	</div>
				</c:forEach> 
				<a href="new-address?contactId=<c:out value='${contact.contactId}' />">
                   	add new address
				</a>
				
				</c:if> 
				
				
				<button type="submit" style="display: block; margin-top: 5; width: 100%;" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
		<div id='errText-container' class='errText-container' style="margin-top: 10px;">
        <div id="errText"></div>
      </div>
	</div>	
</body>
</html>