
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%@ include file="/WEB-INF/nav.jsp" %>
<div class="container col-md-5" style="margin-top: 50px;">
		<div class="card" >
			<div class="card-body"">
			<form id="contactForm" action="insert-address">

				<input type="hidden" name="contactId" value="<c:out value='${contact.contactId}' />" />

				<fieldset class="form-group">
					<label>Address Line1</label> <input type="text" id="name"
						value="<c:out value='${address.addressLine1}' />" class="form-control"
						name="addressLine1">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Address Line2</label> <input type="text" id="name"
						value="<c:out value='${address.addressLinew}' />" class="form-control"
						name="addressLine2">
				</fieldset>
				
				<fieldset class="form-group">
					<label>City</label> <input type="text" id="name"
						value="<c:out value='${address.city}' />" class="form-control"
						name="city">
				</fieldset>
				
				<fieldset class="form-group">
					<label>State</label> <input type="text" id="name"
						value="<c:out value='${address.state}' />" class="form-control"
						name="state">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Postal Code</label> <input type="text" id="name"
						value="<c:out value='${address.postalCode}' />" class="form-control"
						name="postalCode">
				</fieldset>
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