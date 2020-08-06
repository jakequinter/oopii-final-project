
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<body>
<%@ include file="nav.jsp" %>
<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
		 		<c:if test="${contact != null}"> 
					<form id="contactForm" onsubmit='return validateContactUpdate();'>
				</c:if>  
				<c:if test="${contact == null}">  
					<form id="contactForm" onsubmit='return validateContactInsert();'>
 				</c:if>

				<caption>
					<h2>
						<c:if test="${contact != null}">
            			Edit <c:out value='${contact.fullName}' />
            		</c:if>
						<c:if test="${contact == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${contact != null}">
					<input type="hidden" name="id" value="<c:out value='${contact.id}' />" />
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

				<fieldset class="form-group">
					<label>Address</label> <input type="text" id="address"
						value="<c:out value='${contact.address}' />" class="form-control"
						name="address">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
		<div id='errText-container' class='errText-container' style="margin-top: 10px;">
        <div id="errText"></div>
      </div>
	</div>
	
	<script>
	  let contactForm = document.getElementById("contactForm");
	  let contact = document.getElementById("contact");
	  let firstName = document.getElementById('firstName');
	  let lastName = document.getElementById('lastName');
	  let address = document.getElementById('address');
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
		  
		  if (address.value === "") {
			  console.log("ERROR!");
			  setError("Please enter an address name");
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
		  
		  if (address.value === "") {
			  console.log("ERROR!");
			  setError("Please enter an address name");
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