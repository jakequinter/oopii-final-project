
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Address Book | Contact</title>
<body>
    <%@ include file="/WEB-INF/nav.jsp" %>
<div class="container col-md-5" style="margin-top: 50px;">
		<div class="card" >
			<div class="card-body"">
		 		<c:if test="${contact != null}"> 
					<form id="contactForm" onsubmit="return validateContactUpdate();">
				</c:if>  
				<c:if test="${contact == null}">  
					<form id="contactForm" onsubmit="return validateContactInsert();">
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
					<label>First Name</label> <input type="text" id="firstName"
						value="<c:out value='${contact.firstName}' />" class="form-control"
						name="firstName">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Last Name</label> <input type="text" id="lastName"
						value="<c:out value='${contact.lastName}' />" class="form-control"
						name="lastName">
				</fieldset>
				
				<!-- addresses -->
				<c:if test="${contact != null}"> 
				<h6 style="margin-top: 20px;">Addresses</h6>
                <c:forEach var="address" items="${addresses}">
                  <div class="input-group" style="margin-bottom: 15px;">
					  <input type="text" readonly class="form-control" value='<c:out value='${address.addressLine1}' /> <c:out value='${address.addressLine2}' />, <c:out value='${address.city}' />, <c:out value='${address.state}' />, <c:out value='${address.postalCode}' />' >
					  <div class="input-group-append" id="button-addon4">
						 <a style="color: #686868; text-decoration: none; margin:11px"  href="edit-address?fkAddressContactId=<c:out value='${contact.contactId}' />&addressId=<c:out value='${address.addressId}' />" role="button">
					      <i class="fas fa-edit"></i>
					    </a>
					    
					    <a style="color: #FF5E5E;; text-decoration: none; margin: 11px;"  href="delete-address?addressId=<c:out value='${address.addressId}' />&contactId=<c:out value='${contact.contactId}' />">
					      <i class="fas fa-trash-alt"></i>
                  		</a> 
					  </div>
                	</div>
				</c:forEach> 
				<a class="btn btn-outline-primary btn-sm" href="<%=request.getContextPath()%>/new-address?contactId=<c:out value='${contact.contactId}' />" >Add Address</a>

				<!-- phone numbers -->
				<h6 style="margin-top: 20px;">Phone Numbers</h6>
                <c:forEach var="phoneNumber" items="${phoneNumbers}">
                  <div class="input-group" style="margin-bottom: 15px;">
					  <input type="text" readonly class="form-control" value='<c:out value='${phoneNumber.phoneNumber}' />' >
					  <div class="input-group-append" id="button-addon4">
					    <a style="color: #686868; text-decoration: none; margin:11px"  href="edit-phonenumber?fkPhoneNumberContactId=<c:out value='${contact.contactId}' />&phoneNumberId=<c:out value='${phoneNumber.phoneNumberId}' />" role="button">
					      <i class="fas fa-edit"></i>
					    </a>
					    
					    <a style="color: #FF5E5E;; text-decoration: none; margin: 11px;"  href="delete-phonenumber?phoneNumberId=<c:out value='${phoneNumber.phoneNumberId}' />&contactId=<c:out value='${contact.contactId}' />">
					      <i class="fas fa-trash-alt"></i>
                  		</a> 
					  </div>
                	</div>
				</c:forEach>
				<a class="btn btn-outline-primary btn-sm" " href="<%=request.getContextPath()%>/new-phonenumber?contactId=<c:out value='${contact.contactId}' />" >Add Number</a>
				
				<!-- emails -->
				<h6 style="margin-top: 20px;">Emails</h6>
                <c:forEach var="email" items="${emails}">
                  <div class="input-group" style="margin-bottom: 15px;">
					  <input type="text" readonly class="form-control" value='<c:out value='${email.email}' />' >
					  <div class="input-group-append" id="button-addon4">
					    <a style="color: #686868; text-decoration: none; margin:11px"  href="edit-email?fkEmailContactId=<c:out value='${contact.contactId}' />&emailId=<c:out value='${email.emailId}' />" role="button">
					      <i class="fas fa-edit"></i>
					    </a>
					    
					    <a style="color: #FF5E5E;; text-decoration: none; margin: 11px;"  href="delete-email?emailId=<c:out value='${email.emailId}' />&contactId=<c:out value='${contact.contactId}' />">
					      <i class="fas fa-trash-alt"></i>
                  		</a> 
					  </div>
                	</div>
				</c:forEach>
				<a class="btn btn-outline-primary btn-sm" " href="<%=request.getContextPath()%>/new-email?contactId=<c:out value='${contact.contactId}' />" >Add Email</a>
				
				</c:if> 
				
				
				<button type="submit" style="display: block; margin-top: 20px; width: 100%;" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
		
		<div id='errText-container' class='errText-container' style="margin-top: 10px;">
        	<div id="errText"></div>
        </div>
	</div>

	<!-- javascript -->
	<script>
	  let contactForm = document.getElementById("contactForm");
	  let firstName = document.getElementById('firstName');
	  let lastName = document.getElementById('lastName');
	  const errTextContainer = document.getElementById('errText-container');
	  const errText = document.getElementById('errText');
	  
	  function validateContactInsert() {
		  if (firstName.value === "") {
			  console.log("ERROR!");
			  setError("Please enter a first name");
			  clearError();
			  return false;
		  }
		  
		  if (hasNumber(firstName.value)) {
			  setError("Please enter a valid first name");
			  clearError();
			  return false;
		  }
		  
		  if (lastName.value === "") {
			  console.log("ERROR!");
			  setError("Please enter a last name");
			  clearError();
			  return false;
		  }
		  
		  if (hasNumber(lastName.value)) {
			  setError("Please enter a valid last name");
			  clearError();
			  return false;
		  }
		  
		  
		  contactForm.action = "insert"; 
		  return true;
	  }
	  
	  function validateContactUpdate() {
		  if (firstName.value === "") {
			  console.log("ERROR!");
			  setError("Please enter a first name");
			  clearError();
			  return false;
		  }
		  
		  if (hasNumber(firstName.value)) {
			  setError("Please enter a valid first name");
			  clearError();
			  return false;
		  }
		  
		  if (lastName.value === "") {
			  console.log("ERROR!");
			  setError("Please enter a last name");
			  clearError();
			  return false;
		  }
		  
		  if (hasNumber(lastName.value)) {
			  setError("Please enter a valid last name");
			  clearError();
			  return false;
		  }
		  
		  
		  contactForm.action = "update"; 
		  return true;
	  }
	  
	  function hasNumber(myString) {
		  return /\d/.test(myString);
		}
	  
	  function setError(msg) {
		  errTextContainer.setAttribute("class", "alert alert-danger");
		  errText.innerText = msg;
		}
	  
	  function clearError() {
		  setTimeout(function() {
			  errTextContainer.setAttribute("class", "");
		    errText.innerText = ''
		  }, 3000)
		}
	</script>
</body>
</html>